
public class Decoders {
	public static Instruction decodeOpcode(short instruction) {
		int lsb = instruction % 2;
		boolean function = false;
		if (lsb == 1) {
			function = true;
		}
		int temp = (int)(Math.pow(2, 13));
		int opcode = (instruction / temp );
		return new Instruction(opcode, function);
	}
	
	public static int decodeDestinationRegisterAddress(short instruction) {
		return getAddressAt(instruction, 9);
	}
	public static int decodeSourceRegisterAddress(short instruction) {
		return getAddressAt(instruction, 5);
	}
	public static int decodeSecondSourceRegisterAddress(short instruction) {
		return getAddressAt(instruction, 1);
	}
	
	private static int getAddressAt(short instruction, int offset) {
	  int temp = (int) (Math.pow(2, offset));
	  return (instruction / temp) % 16;
	}
	private static int getaluSrc(short instruction) {
		return (instruction / 2) % 32;
	}

	public static short decodeJumpAddress(short instruction) {
		return (short) (instruction % Math.pow(2, 9));
	}	
		
		
}

