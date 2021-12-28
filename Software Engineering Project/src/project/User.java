package project;



public class User {
	String name;
	String status;
	String statusdescription;
	int phone;
	int id;

	public User(String name, String status, int id, int phone) throws InvalidInputException{
		this.name = name;
		this.status = status;
		if(id > 9999999 || id < 1000000) {
			throw new InvalidInputException("Please enter a 7-digit id number for "+name);
		}
		this.id = id;

	}



	public User(int id)  throws InvalidInputException{
		if(id > 9999999 || id < 1000000) {
			throw new InvalidInputException("Please enter a 7-digit id number instead of"+id);
		}
		this.id = id;
	}

	public User(int id, String status) throws InvalidInputException {
		this.status = status;
		if(id > 9999999 || id < 1000000) {
			throw new InvalidInputException("Please enter a 7-digit id number instead of "+id);
		}
		this.id = id;
	
	}

	public User(String status) {
		this.status = status;
	}
	public User(int id,String status,String statusdescription) {
		this.id=id;
		this.status = status;
		this.statusdescription=statusdescription;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) throws InvalidInputException{
		if(name==null) {
			throw new InvalidInputException("Please enter a valid name");
		}
		this.name = name;
		System.out.println("user name is set to "+name);
	}

	public String getStatus() {
		return status;
	}
	

	public String getStatusdescription() {
		return statusdescription;
	}

	public void setStatusdescription(String statusdescription) throws InvalidInputException {
		if(statusdescription==null) {
			throw new InvalidInputException("Please enter a valid user description");
		}
		this.statusdescription = statusdescription;
	}

	public void setStatus(String status) throws InvalidInputException{
		if(status==null) {
			throw new InvalidInputException("Please enter a valid user status");
		}
		this.status = status;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
		System.out.println("user phone number is set to "+phone);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) throws InvalidInputException {

		if(id > 9999999 || id < 1000000) {
			throw new InvalidInputException("Please enter a 7-digit id number instead of"+id);

		}

		this.id = id;
		System.out.println("Id succesfully set :" + id);

	}

	public String Report_prb(String status) throws InvalidInputException {
		if(status==null) {
			throw new InvalidInputException("Please enter a valid user status");
		}
		if (status == "has problem") {
			return ("problem  reported");
		} else {
			return ("No problem to report");

		}
	}
}