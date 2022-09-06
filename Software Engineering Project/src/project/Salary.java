package project;

public class Salary {
Staff staff;
int SalaryValue;


public Salary( Staff staff, int SalaryValue) {
	this.staff = staff;
	this.SalaryValue= SalaryValue;
}


public Staff getStaff() {
	return staff;
}


public void setStaff(Staff staff) {
	this.staff = staff;
}


public int getSalaryValue() {
	return SalaryValue;
}


public void setSalaryValue(int salaryValue) {
	SalaryValue = salaryValue;
	System.out.println("the current salary value is " +salaryValue);
}


 int deduction (int SalaryValue,int value) {
	 int Salary = SalaryValue - value;
	 System.out.println("After deduction, the salary value is " + Salary);
	 return Salary;
 }
int withdraw (int SalaryValue,int value) {
	int Salary = SalaryValue - value;
	System.out.println("After withdrawal, the salary value is " + Salary);
	 return Salary;
}
int addition (int SalaryValue,int value) {
	int Salary = SalaryValue + value;
	System.out.println("After addition, the salary value is " + Salary);
	return Salary;
}



public String toString() {
	return "Salary [staff=" + staff + ", SalaryValue=" + SalaryValue + "]";
}


}
