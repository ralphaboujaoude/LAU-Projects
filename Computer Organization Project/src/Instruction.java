
public class Instruction {
	public static final String ADD = "add";
	public static final String SUB = "sub";
	public static final String LW = "lw";
	public static final String LWI = "lwi";
	public static final String SW = "sw";
	public static final String BEQ = "beq";
	public static final String BGT = "bgt";
	public static final String J = "j";
	
	private String value;
	
	public Instruction(int opcode, boolean sub) {
		this.value = mapOpcode(opcode, sub);
	}
	
	public String getValue() {
		return value;
	}
	
	private String mapOpcode(int opcode, boolean sub) {
		switch(opcode) {
		case 0:if(sub) {
			
				return SUB;
			}else
			return ADD;
		case 1:
			return LW;
		case 2:
			return LWI;
		case 3:
			return SW;
		case 4:
			return BEQ;
		case 5:
			return BGT;
		case 6:
			return J;
		default:
			throw new RuntimeException("Opcode does not exist");
		}
	}
}
