package project;

public class studentGrade {
int studentId;
String courseName;


public studentGrade(int studentId, String courseName) throws InvalidInputException {
	this.courseName=courseName;
	if(studentId > 9999999 ||studentId < 1000000) {
		throw new InvalidInputException("Please enter a 7-digit valid id number ");
	}
	this.studentId = studentId;
	
}


public int getStudentId() {
	return studentId;
}


public void setStudentId(int studentId)  throws InvalidInputException{
		 if(studentId > 9999999 ||studentId < 1000000) {
				throw new InvalidInputException("Please enter a valid 7-digit id number ");
			}
			this.studentId = studentId;
			System.out.println("ID successfully set" + studentId);
	 }
public String getCourseName() {
	return courseName;
}


public void setCourseName(String courseName) {
	this.courseName = courseName;
}



public void GenerateStudentGrades( int  StudentID, int grade){

if(grade>=80)
        {
            System.out.print("A");
        }
        else if(grade>=60 && grade<80)
        {
           System.out.print("B");
        } 
        else if(grade>=40 && grade<60)
        {
            System.out.print("C");
        }
        else
        {
            System.out.print("D");
        }


}
}