import java.util.Scanner;

public class Login {
	
	/** Requests login information from the user. Terminates the program after
	 * too many failed login attempts.
	 * 
	 */
	public User loginUser(dbManager db)
	{
		String input1,input2;
		int try_counter = 0;
		boolean successful_login = false;
		Scanner user_input = null;
		
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
			user_input = new Scanner(System.in); 
			input1 = user_input.next();
			
			System.out.print("Password: ");
			input2 = user_input.next();
			
			User user = db.loadUser(input1, input2);
			
			if (user != null)
			{
				System.out.println("Welcome " + input1 + "!");
            	successful_login = true;
            	user_input.close();
            	
            	return user;
			}
		}
		
		user_input.close();
		
		return null;		
	}
}