package project;

public class BusinessOfficeandAdministration extends Subsystem{
	String name;
	String locaton;
	String number;
	
	
	public BusinessOfficeandAdministration(String name, String Location, String number) {
		this.name = name;
		this.Location = Location;
		this.number = number;

	}


	
	public String toString() {
		return "BusinessOfficeandAdministration [name=" + name + ", locaton=" + locaton + ", number=" + number + "]";
	}

}
