import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Registers registers= new Registers();
		Scanner scan;
		try {
			scan = new Scanner(new File("input.txt"));
			for (int i = 0; i < 8; i++) {
				registers.addreg(scan.nextShort(), i);
			}
			Datapath datapath=new Datapath();
			datapath.setRegisters(registers);
			while(scan.hasNext()) {
				datapath.execute((short) Integer.parseInt(scan.next(),2));
			}
			
			for (int i = 0; i < 8; i++) {
				System.out.println(registers.get(i));
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
