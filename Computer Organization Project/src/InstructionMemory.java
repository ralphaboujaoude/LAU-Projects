
public class InstructionMemory {
	private short[] memory;

	public InstructionMemory( ) {
		memory = new short[] {};
	}
	
	public short read(short address) {
		return memory[address];
	}
}
