import org.omg.Messaging.SyncScopeHelper;

public class Datapath {

	private static InstructionMemory im = new InstructionMemory();
	private static DataMemory dm = new DataMemory();
	
	private static ALU alu;
	private static Shiftleft sl = new Shiftleft();
	private static Adder adder = new Adder();
	
	private static Registers registers = new Registers();
	
	private static Controller controller = new Controller();
	
	private static ProgramCounter pc = new ProgramCounter();
	public void setRegisters(Registers registers) {
		Datapath.registers=registers;
	}
	public void execute(short instruction) {
		controller.generateControlValues(instruction);
		short signExtended=0;
		
		Multiplexer regDstDestination = new Multiplexer(
		
				(short)Decoders.decodeSecondSourceRegisterAddress(instruction),
				(short)Decoders.decodeDestinationRegisterAddress(instruction));
		
		registers.updateAddresses(Decoders.decodeSourceRegisterAddress(instruction), 
				Decoders.decodeSecondSourceRegisterAddress(instruction),
				regDstDestination.pickInput(controller.isRegDst()));
		
		Multiplexer aluSrc = new Multiplexer(registers.getReadReg2(), signExtended);
		alu = new ALU(registers.getReadReg1(), aluSrc.pickInput(controller.isAluSrc()));
		
		short aluResult = computeAluOutput(controller.isAluOp());
		
		boolean aluBoolResult = false;
		if (controller.isBranch()) {
			aluBoolResult = computeAluBooleanResult(controller.isAluEqual());
		}
		
		dm.updateAddress(aluResult);
		short memResult = 0;
		if (controller.isMemWrite()) {
			dm.write(registers.getReadReg2(), aluResult);
		} else if (controller.isMemRead()) {
			memResult = dm.read(aluResult);
		}
		
		Multiplexer memToReg = new Multiplexer(aluResult, memResult);
		registers.writeData(memToReg.pickInput(controller.isMemToReg()), controller.isRegWrite());
		
		short branchAddress = adder.add((short) (signExtended * 2) , pc.incrementAndGet());
		Multiplexer branchMultiplexer = new Multiplexer(pc.getValue(), branchAddress);
		
		short nextAddress = branchMultiplexer.pickInput(controller.isBranch() && aluBoolResult);
		Multiplexer finalAddress = new Multiplexer(nextAddress,
				(short) (2*Decoders.decodeJumpAddress(instruction))
				);
		pc.setValue(finalAddress.pickInput(controller.isJump()));
		
	}

	private boolean computeAluBooleanResult(boolean aluEqual) {
		if (aluEqual) {
			return alu.isEqual();
		}
		return alu.isGreaterThan();
	}

	private short computeAluOutput(boolean aluOp) {
		if (aluOp) {
			return alu.sub();
		}
		return alu.add();
	}
	
}
