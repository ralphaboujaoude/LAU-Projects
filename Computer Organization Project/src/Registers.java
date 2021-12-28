
public class Registers {

	private Register[] registers = new Register[8];
	private int src, secondSrc, destination;
	
	public void updateAddresses(int src, int secondSrc, int destination) {
		this.src = src;
		this.secondSrc = secondSrc;
		this.destination = destination;
	}
public void addreg(short value,int regnum) {
	Register register= new Register();
	register.setValue(value);
	registers[regnum]=register;

}
	public short getReadReg1() {
		
		return registers[src].getValue();
	}

	public short getReadReg2() {
		return registers[secondSrc].getValue();
	}
	
	public void writeData(short data, boolean write) {
		if (write) {
			registers[destination].setValue(data);
		}
		
	}
	public short get(int pos) {
		return registers[pos].getValue();
	}
}
