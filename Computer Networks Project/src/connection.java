import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class connection {

	public static void main(String[] args) {

		System.out.print(connect());
	}
	public static String connect(){
		String s="";
		try {
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Networks","root","71390051L@u");
			System.out.print("\n");
			java.sql.Statement stm= con.createStatement();
			ResultSet rs=  stm.executeQuery("SELECT * FROM users");
			
			while(rs.next()) {
				String name=rs.getString("username");
				String name1=rs.getString("password");
				s=name+"     "+name1;
				
			}
			
			
		} catch (SQLException e) {
			System.out.print("error");
			e.printStackTrace();
		}
		return s;
	}
	
	
	public static String login(String username, String pass){
		
		try {
			
		
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Networks","root","71390051L@u");
			System.out.print("\n");
			java.sql.Statement stm= con.createStatement();
			ResultSet rs=  stm.executeQuery("SELECT * FROM users");
			String s="";
			while(rs.next()) {
			

				if(rs.getString("username").equalsIgnoreCase(username) && rs.getString("password").equalsIgnoreCase(pass)) {
					s="username: "+ rs.getString("username")+ "  name: "+rs.getString("name")+"  email: "+rs.getString("email") +"  address: "+rs.getString("address")+"  Vaccination status: "+rs.getString("Vstatus")+"  Infected status: "+rs.getString("Infectedstatus");		
					return s;				
					}
			}
			
			
		} catch (SQLException e) {
			System.out.print("error");
			e.printStackTrace();
		}
		return "Incorrect";
		}
	
public static String register(String username, String pass,String name, String address,String email, String Vstatus,String Vurl,String Istatus){
	String s="";
	try {
		
	
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Networks","root","71390051L@u");
		System.out.print("\n");
		java.sql.Statement stm= con.createStatement();
		ResultSet rs=  stm.executeQuery("SELECT * FROM users");
		while(rs.next()) {
			
			if(rs.getString("username").equalsIgnoreCase(username)) {
				return "Already exists";
			}
		}
		
		String insert="INSERT INTO users(username,password,name,address,email,Vstatus,Vurl,Infectedstatus) "
				+ "VALUES('"+username+"','"+pass+"','"+name+"','"+address+"','"+email+"','"+Vstatus+"','"+Vurl+"','"+Istatus+"')";
		java.sql.Statement stm1=con.createStatement();
		
		boolean signup= stm1.execute(insert);
		stm1.close();
		
	} catch (SQLException e) {
		System.out.print("error");
		e.printStackTrace();
	}
	 s="username: "+ username+ "  name: "+name+"  email: "+email +"  address: "+address+"  Vaccination status: "+Vstatus+"  Infected status: "+Istatus;
	 return s;
	}

public static int Get_Total_Cases() {
	int x=0;

	try {
		
	
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Networks","root","71390051L@u");
		System.out.print("\n");
		java.sql.Statement stm= con.createStatement();
		ResultSet rs=  stm.executeQuery("SELECT * FROM users");
		while(rs.next()) {
		
			if(rs.getString("Infectedstatus").equalsIgnoreCase("contagious")) {
				x++;
				
			}
		}
		
	} catch (SQLException e) {
		System.out.print("error");
		e.printStackTrace();
	}
	return x;
	}


public String Update_Status(String username,String Ustatus) {
	int x=0;

	try {
		
	
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Networks","root","71390051L@u");
		System.out.print("\n");		
		String update="UPDATE users SET Infectedstatus = '"+Ustatus+"' WHERE username = '"+username+"'";
		java.sql.Statement stm1=con.createStatement();
				
				boolean signup= stm1.execute(update);
				stm1.close();
				return "Succesfull";
		
	} catch (SQLException e) {
		System.out.print("error");
		e.printStackTrace();
	}
	return "Error";
	}

public String Share_PCR_URL(String username,String url) {
	int x=0;

	try {
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Networks","root","71390051L@u");
		System.out.print("\n");		
		java.sql.Statement stm= con.createStatement();
		ResultSet rs=  stm.executeQuery("SELECT * FROM user_pcrs");
		while(rs.next()) {
			
			if(rs.getString("username").equalsIgnoreCase(username)) {
				String update="UPDATE user_pcrs SET pcr_url='"+url+"' WHERE username ='"+username+"'";
				java.sql.Statement stm1=con.createStatement();
				boolean signup= stm1.execute(update);
				stm1.close();
				return "Updated succesfully";
			}
		}
		
		
		
		String insert="INSERT INTO user_pcrs(username,pcr_url) VALUES('"+username+"','"+url+"')";
		java.sql.Statement stm1=con.createStatement();
				
				boolean signup= stm1.execute(insert);
				stm1.close();
				return "created succesfuly";
		
	} catch (SQLException e) {
		System.out.print("error");
		e.printStackTrace();
	}
	return "Error";
	}

	
	

public  String Add_Trusted_People(String username,String username_t){
String s="";
try {
	

	Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Networks","root","71390051L@u");
	System.out.print("\n");
	java.sql.Statement stm= con.createStatement();
	ResultSet rs=  stm.executeQuery("SELECT * FROM users");
	while(rs.next()) {
		
		if(rs.getString("username").equalsIgnoreCase(username_t)) {
			String insert="CREATE TABLE IF NOT EXISTS "+username+"( trusted varchar(250))";
					
			java.sql.Statement stm1=con.createStatement();
			boolean signup= stm1.execute(insert);
			stm1.close();
			String insert1="INSERT INTO "+username+" VALUES ('"+username_t+"')";

			java.sql.Statement stm2=con.createStatement();
			
			stm2.execute(insert1);
			stm2.close();
			return "Added succesfully";
		}
	}
	
	
	
} catch (SQLException e) {
	System.out.print("error");
	e.printStackTrace();
}
 return "Trusted username not found";
}



public  String Check_Trusted_health_Status(String username,String username_t){
String s="";
try {
	

	Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Networks","root","71390051L@u");
	System.out.print("\n");
	java.sql.Statement stm= con.createStatement();
	ResultSet rs=  stm.executeQuery("SELECT * FROM "+username_t);
	while(rs.next()) {
		
		if(rs.getString("trusted").equalsIgnoreCase(username)) {
			java.sql.Statement stm1= con.createStatement();
			ResultSet rs1=  stm1.executeQuery("SELECT Infectedstatus FROM users WHERE username='"+username_t+"'");
			rs1.next();
			return rs1.getString("Infectedstatus");
					
			}
	}
	
	
	
} catch (SQLException e) {
	System.out.print("error");
	e.printStackTrace();
}
 return "You are Not Trusted ";
}

}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*public static String connect2(){
		String s="";
		try {
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","root");
			
			Statement stm=(Statement) con.createStatement();
			ResultSet rs= ((java.sql.Statement) stm).executeQuery("SELECT * FROM employee");
			while(rs.next()) {
				String name=rs.getString("first_name");
				String name1=rs.getString("last_name");
				String name2=rs.getString("employee_id");
				String name3=rs.getString("Salary");
				s=s+"\n"+"|"+name2+"|"+name+"  "+name1+"  $"+name3;

			
			
			
		} catch (SQLException e) {
			System.out.print("error");
			e.printStackTrace();
		}
		return s;
	}
	*/