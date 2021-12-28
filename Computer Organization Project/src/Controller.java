
public class Controller {
	private boolean regDst;
	private boolean jump;
	private boolean branch;
	private boolean memRead;
	private boolean memToReg;
	private boolean aluOp ;
	private boolean aluEqual;
	
	private boolean memWrite;
	private boolean aluSrc;
	private boolean regWrite;
	
	public void generateControlValues(short instruction) {
		Decoders decoder = new Decoders();
		Instruction inst = decoder.decodeOpcode(instruction);
		switch (inst.getValue()) {
		case Instruction.ADD: 
		{
			this.regDst = true;
			this.jump = false;
			this.branch = false;
			this.memToReg = false;
			this.aluOp = false;
			this.memWrite = false;
			this.aluSrc = false;
			this.regWrite = true;
			break;
		}
		case Instruction.SUB: 
		{
			this.regDst = true;
			this.jump = false;
			this.branch = false;
			this.memToReg = false;
			this.aluOp = true;
			this.memWrite = false;
			this.aluSrc = false;
			this.regWrite = true;
			break;
		}
		case Instruction.LW: 
		{
			this.regDst = false;
			this.jump = false;
			this.branch = false;
			this.memToReg = true;
			this.aluOp = false;
			this.memWrite = false;
			this.aluSrc = true;
			this.regWrite = true;
			break;
		}case Instruction.LWI: 
		{
			this.regDst = false;
			this.jump = false;
			this.branch = false;
			this.memToReg = true;
			this.aluOp = false;
			this.memWrite = false;
			this.aluSrc = true;
			this.regWrite = true;
			break;
		}case Instruction.SW: 
		{
			this.regDst = true;
			this.jump = false;
			this.branch = false;
			this.memToReg = true;
			this.aluOp = false;
			this.memWrite = true;
			this.aluSrc = false;
			this.regWrite = true;
			break;
		}case Instruction.BEQ: 
		{
	
			this.aluEqual = true;
			this.jump = false;
			this.branch = true;
			this.memToReg = false;
			this.aluOp = true;
			this.memWrite = false;
			this.aluSrc = false;
			this.regWrite = false;
			break;
		}case Instruction.BGT: 
		{
			this.aluEqual = false;
			this.jump = false;
			this.branch = true;
			this.memToReg = false;
			this.aluOp = true;
			this.memWrite = false;
			this.aluSrc = false;
			this.regWrite = false;
			break;
		}case Instruction.J: 
		{
			this.regDst = true;
			this.jump = false;
			this.branch = false;
			this.memToReg = false;
			this.aluOp = false;
			this.memWrite = false;
			this.aluSrc = false;
			this.regWrite = true;
			break;
		}
		default :
			throw new RuntimeException("Instruction is unknown to the controller");
		}
		
	}

	public boolean isRegDst() {
		return regDst;
	}

	public boolean isJump() {
		return jump;
	}

	public boolean isBranch() {
		return branch;
	}

	public boolean isMemRead() {
		return memRead;
	}

	public boolean isMemToReg() {
		return memToReg;
	}

	public boolean isAluOp() {
		return aluOp;
	}

	public boolean isMemWrite() {
		return memWrite;
	}

	public boolean isAluSrc() {
		return aluSrc;
	}

	public boolean isRegWrite() {
		return regWrite;
	}

	public boolean isAluEqual() {
		return aluEqual;
	}
}
