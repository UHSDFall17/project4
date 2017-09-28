import java.util.Scanner;
import java.io.*;


public class CreateAccount {//Java Class designed to create a user account
	
	private String username;
	private String password;
	
	public CreateAccount()
	{
		username = "N/A";
		password = "N/A";
	}
	public void checkValid()
	{//Checks Text File if username is not taken
		
	}
	public void setAccount()//Sets Variables username and password
	{		
		System.out.print("Type in your username: ");
		Scanner user = new Scanner(System.in);
		username = user.next();
		
		System.out.println();
		System.out.print("Type in your password: ");
		Scanner pass = new Scanner(System.in);
		password = pass.next();
		
	}
	public void insertAccount() //Inserts Account into the text file login.txt
	{
		String temp_user = "N/A",temp_pass = "N/A";
		FileWriter out;
		
		try{
			out = new FileWriter("login.txt",true);
			
			out.write(temp_user + " " + temp_pass);
			out.write("\n");
			
			
			out.close();
			
		}catch(IOException e)
		{
			System.out.println("Error in opening file. Terminating Program.");
			System.exit(0);
		}
	}
	
}
