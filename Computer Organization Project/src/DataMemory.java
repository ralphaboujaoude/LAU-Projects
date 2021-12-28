import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataMemory {
	private short[] memory;
	private int address;

	public DataMemory() {
		
		try {
			Scanner scan= new Scanner(new File("Memory.txt"));
			int i=0;
			 memory=new short[100];
			while(scan.hasNext()) {
				memory[i]=(short)scan.nextInt();
				i+=1;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void write(short value, short address) {
		memory[address] = value;
	}
	
	public short read(short address) {
		return memory[address];
	}
	
	public void updateAddress(short address) {
		this.address = (int) (address);
	}
}
