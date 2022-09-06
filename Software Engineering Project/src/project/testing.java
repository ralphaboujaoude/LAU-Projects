package project;
import java.util.*;
public class testing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			StaffList stafflist= new StaffList();
			Staff ahmad = new Staff ("ahmad", 1111111, 03233232,40,"IT");
			Staff nour= new Staff("nour", 8593849, 70778899, 55);
			Staff lynn = new Staff (4455667);
			
			stafflist.StaffAdd(nour.getStaff_ID());
			stafflist.StaffAdd(ahmad.getStaff_ID());
			stafflist.StaffAdd(lynn.getStaff_ID());
			
			System.out.println("The current staff list is:");
			stafflist.printStaffList();
	
			stafflist.StaffRemove(lynn.getStaff_ID());
			stafflist.printStaffList();
			System.out.println("Staff lynn removed");
			
		System.out.println(stafflist.StaffCount());	
			
		BusinessOfficeandAdministration office = new BusinessOfficeandAdministration("administration","Nicol Hall", "10101");
		FA_Budget budget = new FA_Budget(10000, 7000000,0);
		budget.calcBudget(budget.getExpenses(), budget.getIncome());
		Salary x = new Salary(lynn,5000);
		
		x.deduction(x.getSalaryValue(),100);
		studentGrade g = new studentGrade(1919191, "physics");
		g.GenerateStudentGrades(1919191, 70);
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of students that have attended their work:");
		int[] attlist = new int[scan.nextInt()];
		System.out.println("Enter their ids(7 digits):");
		for (int i = 0; i < attlist.length; i++) {
			attlist[i] = scan.nextInt();
			if (attlist[i] > 9999999 || attlist[i] < 1000000) {
				throw new InvalidInputException("please enter a valid 7-digit id number instead of " + attlist[i]);
			}
		}
		System.out.println("Enter the number of students that are accepted in financial aid:");
		int[] acclist = new int[scan.nextInt()];
		System.out.println("Enter their ids(7 digits):");
		for (int i = 0; i < acclist.length; i++) {
			acclist[i] = scan.nextInt();
			if (acclist[i] > 9999999 || acclist[i] < 1000000) {
				throw new InvalidInputException("please enter a valid 7-digit id number instead of " + acclist[i]);
			}
		}
		System.out.println("Enter their ids(7 digits):");
		int[] doclist = new int[scan.nextInt()];
		System.out.println("Enter their ids:");
		for (int i = 0; i < doclist.length; i++) {
			doclist[i] = scan.nextInt();
			if (doclist[i] > 9999999 || doclist[i] < 1000000) {
				throw new InvalidInputException("please enter a valid 7-digit id number instead of " + doclist[i]);
			}
		}
		System.out.println("Enter the number of students that have probation:");
		int[] problist = new int[scan.nextInt()];
		System.out.println("Enter their ids:");
		for (int i = 0; i < problist.length; i++) {
			problist[i] = scan.nextInt();
			if (problist[i] > 9999999 || problist[i] < 1000000) {
				throw new InvalidInputException("please enter a valid 7-digit id number instead of " + problist[i]);
			}
		}
		System.out.println("Enter the number of students that have incomplete information:");
		int[] incomplist = new int[scan.nextInt()];
		System.out.println("Enter their ids(7 digits):");
		for (int i = 0; i < incomplist.length; i++) {
			incomplist[i] = scan.nextInt();
			if (incomplist[i] > 9999999 || incomplist[i] < 1000000) {
				throw new InvalidInputException(
						"please enter a valid 7-digit id number instead of " + incomplist[i]);
			}
		}
		UserList SysUsers = new UserList();
		User Mira = new User(2017090, "No problem");
		User Ralph = new User(2017289, "has problem");
		User Nashaat = new User(1111111, "has problem");
		User Ali = new User(2017100, "No problem");
		SysUsers.useradd(Mira.getId());
		SysUsers.useradd(Ralph.getId());
		SysUsers.useradd(Ali.getId());
		System.out.println("Your Current Ids List is :");
		SysUsers.printUsersList();
		System.out.println("Mira is removed and Nashaat is added :");
		Mira.setId(1238908);
		SysUsers.Useremove(Mira.getId());
		SysUsers.useradd(Nashaat.getId());
		System.out.println("your new Users List is :");
		SysUsers.printUsersList();
		System.out.println("Is mira Still in Users List");
		System.out.println(SysUsers.UserCheck(SysUsers.head, Mira.getId()));
		System.out.println("Does Mira have a problem ?");
		System.out.println(Mira.Report_prb(Mira.getStatus()));
		Mira.setId(12345607);
		System.out.println(SysUsers.UserCheck(SysUsers.head, Mira.getId()));
		SysUsers.attendance(scan.nextInt(), attlist);
		SysUsers.absStudentRecord(scan.nextInt(), scan.nextInt(), scan.nextInt());
		SysUsers.work(scan.nextInt(),scan.nextInt());
	} 
		

					
					
					
					
					
			
		catch (InvalidInputException x) {
			System.out.println(x);
		
		
}
		}


}
