import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;

import java.net.*;
import java.nio.channels.FileChannel;

public class SingleServer {
	public static void main(String[] args) throws IOException {
		// Server is listening on port 5056
		String user="";

		ServerSocket serverSocket = new ServerSocket(5056);
Scanner scan=new Scanner(System.in);
		Socket clientSocket = null;
		try {
			// ServerSocket waits for a Client to connect
			clientSocket = serverSocket.accept();

			System.out.println("A new client is connected : " + clientSocket);

			// Receiving input and sending output to Client
			DataInputStream inputFromClient = new DataInputStream(clientSocket.getInputStream());
			DataOutputStream outputToClient = new DataOutputStream(clientSocket.getOutputStream());
			BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			// Variables
			String received;
			String toreturn;
			boolean login_bool=false;
			while (true) {
				try {
					if(!login_bool) {
						//SIGNIN PAGE
					// Initiate communication with Client
					outputToClient.writeUTF("Login and Resgister Page:   [Login | register ]..\n" + "Type Exit to terminate connection.");
					// Receive the answer from Client
					received = inputFromClient.readUTF();
					// Receiving Exit closes the connection and breaks the loop
					if (received.equals("Exit")) {
						System.out.println("-----------------------------------------------------------------------------------");
						System.out.println("Client " + clientSocket + " sends exit...");
						System.out.println("Closing this connection.");
						clientSocket.close();
						System.out.println("Connection closed");
						break;
					}
					// Creating and formatting Date object
					connection con=new connection();
					// Send to Client what is requested
					switch (received) {
					case "Login":
						
						System.out.println("username: ");
						String username=scan.next();
					
						System.out.println("password: ");
						String password=scan.next();
						 user =con.login(username,password);
						
                         if(user.equalsIgnoreCase("Incorrect")) {
							 
						 }
						 else {
							 login_bool = true;
							 
						 }
						toreturn = user;
							
						
						outputToClient.writeUTF(toreturn);
						break;
					case "register":
						String Vurl_r="";
						System.out.println("username: ");
						String username_r=scan.next();
						System.out.println("password: ");
						String password_r=scan.next();
						System.out.println("name: ");
						String name_r=scan.next();
						System.out.println("address: ");
						String address_r=scan.next();
						System.out.println("eamil_r: ");
						String email_r=scan.next();
						System.out.println("What is your infected status:\n"
								+ "[contagious]  [safe]  [at_risk] ");
						String istatus_r=scan.next();
						System.out.println("vaccination status if Vaccinated type: [T] if not type [F]: ");
						String vaccinationstatus_r=scan.next();
						if(vaccinationstatus_r.equalsIgnoreCase("T")) {
							System.out.println("please enter your Vaccinated certificate url");
							Vurl_r=scan.next();
							
						}
						
						 user = con.register(username_r, password_r,name_r,address_r,email_r,vaccinationstatus_r,Vurl_r,istatus_r);
					   
						
						toreturn = user;
                        if(user.equalsIgnoreCase("Username already taken")) {
							 
						 }
						 else {
							 login_bool = true;
							 
						 }
						outputToClient.writeUTF(toreturn);
						break;
					default:
						outputToClient.writeUTF("Invalid input");
						break;
					}
				} else {
					//HOME PAGE
					
					// Initiate communication with Client
					outputToClient.writeUTF("Welcome to the Home Page: "
							+ "\n [Get_Total_Cases | Update_Status | Share_PCR_URL | Add_Trusted_People | Check_Trusted_health_Status]..\n" + "Type Exit to terminate connection.");

					// Receive the answer from Client
					received = inputFromClient.readUTF();

					// Receiving Exit closes the connection and breaks the loop
					if (received.equals("Exit")) {
						System.out.println("-----------------------------------------------------------------------------------");
						System.out.println("Client " + clientSocket + " sends exit...");
						System.out.println("Closing this connection.");
						clientSocket.close();
						System.out.println("Connection closed");
						break;
					}

					// Creating and formatting Date object
					connection con=new connection();

					// Send to Client what is requested
					switch (received) {

					case "Get_Total_Cases":
						int num=con.Get_Total_Cases();
						toreturn = String.valueOf(num);
						outputToClient.writeUTF(toreturn);
						break;
					case "Update_Status":
						Pattern p = Pattern.compile("username: (.*?) ");
		                Matcher m = p.matcher(user);
		                String username="";
		              while(m.find()) {
		                 username = m.group(1);
		                }
		              System.out.println("Enter your updated status:"
		              		+ "[safe] [contagious] [at_risk]");
		              	String ustatus=scan.next();
		              	String res=con.Update_Status(username,ustatus);
						toreturn = res;
					
						outputToClient.writeUTF(toreturn);
						break;
						
					case "Share_PCR_URL":
						Pattern p1 = Pattern.compile("username: (.*?) ");
		                Matcher m1 = p1.matcher(user);
		                String username1="";
		              while(m1.find()) {
		                 username1 = m1.group(1);
		                }
						System.out.println("Please enter your PCR URL:");
						String url=scan.next();
						
						String resultt=con.Share_PCR_URL(username1, url);
						toreturn = resultt;
						outputToClient.writeUTF(toreturn);
						break;
						
					case "Add_Trusted_People":
						Pattern p2 = Pattern.compile("username: (.*?) ");
		                Matcher m2 = p2.matcher(user);
		                String username2="";
		                while(m2.find()) {
		                 username2 = m2.group(1);
		                }
						System.out.println("Please enter the username of whome you trusted :");
						String username_t=scan.next();
						String trusted =con.Add_Trusted_People(username2, username_t);
						
						toreturn = trusted;
						outputToClient.writeUTF(toreturn);
						break;
						
					
					case "Check_Trusted_health_Status":
						Pattern p3 = Pattern.compile("username: (.*?) ");
		                Matcher m3 = p3.matcher(user);
		                String username3="";
		                while(m3.find()) {
		                 username3 = m3.group(1);
		                }
						System.out.println("Please enter the username you want to check his health:");
						String username_t1=scan.next();
						String trusted1 =con.Check_Trusted_health_Status(username3, username_t1);
						
						toreturn = trusted1;
						outputToClient.writeUTF(toreturn);
						break;
						
					default:
						outputToClient.writeUTF("Invalid input");
						break;
					}
					///end
					                                  
				}
					} catch (IOException e) {
					e.printStackTrace();
				}
			}

			try {
				// Closing resources
				inputFromClient.close();
				outputToClient.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			clientSocket.close();
			e.printStackTrace();
		}
	}
	}