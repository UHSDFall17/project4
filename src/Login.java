import java.util.Scanner;

public class Login {
	
	/** Requests login information from the user. Terminates the program after
	 * too many failed login attempts.
	 * 
	 */
	public void loginUser(dbManager db)
	{
		String input1,input2;
		int try_counter = 0;
		boolean successful_login = false;
		
		while(try_counter < 3 && successful_login == false)
		{
			try_counter+=1;
			if(try_counter == 3)
			{
				System.out.println("Too many unsuccessful events! Terminating program.");
				System.exit(0);
			}
			
			System.out.println("Type in your username and password to access your account.");
			System.out.print("Username: ");
			Scanner user_input = new Scanner(System.in); 
			input1 = user_input.next();
			
			System.out.print("Password: ");
			input2 = user_input.next();
			
			boolean userVerified = db.verifyUser(input1, input2);
			
			if (userVerified)
			{
				System.out.println("Welcome " + input1 + "!");
            	successful_login = true;
            	user_input.close();
			}			
		}		
	}
}