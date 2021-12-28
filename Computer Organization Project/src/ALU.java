
public class ALU {

	private short first;
	private short second;
	
	public ALU(short first, short second) {
		this.first = first;
		this.second = second;
	}
	
	public short add() {
		return (short) (first + second);
	}
	public short sub() {
		return (short) (first - second);
	}
	public boolean isEqual() {
		return first == second;
	}
	public boolean isGreaterThan() {
		return first > second;
	}
}
