package project;

public class Staff {
	String name ;
	int Staff_ID;
	String Department;
	int PhoneNb;
	int hours;
	int salary;

	public Staff(String name, int Staff_ID, int PhoneNb, int hours, String Department) throws InvalidInputException {
		this.name=name;
		this.Department=Department;
		this.PhoneNb= PhoneNb;
		this.hours= hours;
		if(Staff_ID > 9999999 ||Staff_ID < 1000000) {
			throw new InvalidInputException("Please enter a 7-digit id number for "+name);
		}
		this.Staff_ID = Staff_ID;
		
	}
	
	public Staff(String name, int Staff_ID, int PhoneNb, int hours) throws InvalidInputException {
		this.name=name;
		
		this.PhoneNb= PhoneNb;
		this.hours= hours;
		if(Staff_ID > 9999999 ||Staff_ID < 1000000) {
			throw new InvalidInputException("Please enter a 7-digit id number for "+name);
		}
		this.Staff_ID = Staff_ID;
		System.out.println("Staff with id" +Staff_ID +" was successfully created");
		
	}
	public Staff(int Staff_ID)  throws InvalidInputException{
		if(Staff_ID > 9999999 || Staff_ID < 1000000) {
			throw new InvalidInputException("Please enter a 7-digit id number instead of"+Staff_ID);
		}
		this.Staff_ID = Staff_ID;
	}
	public Staff (int Staff_ID, String name, String Department) throws InvalidInputException {
		this.Department= Department;
		this.name= name;
		if(Staff_ID > 9999999 ||Staff_ID < 1000000) {
			throw new InvalidInputException("Please enter a 7-digit id number for "+name);
		}
		this.Staff_ID = Staff_ID;
		
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws InvalidInputException {
		if (name==null)
			throw new InvalidInputException("Please enter a valid name");
		this.name = name;
		System.out.println("Staff name is set to " +name);
	}
	
	public String getDepartment () {
		return Department;
	}
	
	public void setDepartment(String Department) throws InvalidInputException{
		if (Department== null) {
			throw new InvalidInputException ("Please enter a valid depatment");
		}
		this.Department=Department;
			
	}
	
	public int getPhoneNb() {
		return PhoneNb;
	}
	
	public void setPhoneNb(int PhoneNb) {
		this.PhoneNb = PhoneNb;
		System.out.println("Staff phone number is set to " +PhoneNb);
	}
	
	 public int getHours() {
			return hours;
		}

		public void setHours(int hours) {
			this.hours = hours;
		}

 public int getStaff_ID() {
	 return Staff_ID;
 }
 
 
 

public void setStaff_ID (int Staff_ID) throws InvalidInputException{
	 if(Staff_ID > 9999999 ||Staff_ID < 1000000) {
			throw new InvalidInputException("Please enter a 7-digit id number for "+name);
		}
		this.Staff_ID = Staff_ID;
		System.out.println("ID successfully set" + Staff_ID);
 }
 
 
 public int getSalary() {
	return salary;
}

public void setSalary(int salary) {
	this.salary = salary;
	System.out.println("The salary of" +name +"is"+ salary);
}

public String Assign_task(String task) throws InvalidInputException {
		if(task==null) {
			throw new InvalidInputException("Please enter a valid task");
		}
		if (task == "has problem") {
			return ("problem reported");
		} else {
			return ("No problem to report");

		}}
 
 
 public int totalWorkinghours(int hours, Staff staff) {
	 int total_hours =staff.getHours();
	 return total_hours;
 }


public String toString() {
	return "Staff [name=" + name + ", Staff_ID=" + Staff_ID + ", Department=" + Department + ", PhoneNb=" + PhoneNb
			+ ", hours=" + hours + "]";
}
 
 
 
}

