package project;


import java.util.*;

public class FinancialAid extends Subsystem {
	String name;
	String Location;
	String number;

	public FinancialAid(String name, String Location, String number) {
		this.name = name;
		this.Location = Location;
		this.number = number;

	}

	public void info() {
		System.out.println("my id is" + name);

	}

	public String toString() {
		return "FinancialAidDep [name=" + name + ", Location=" + Location + ", number=" + number + "]";
	}

}