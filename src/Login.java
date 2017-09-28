import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Login {
	Login()
	{
		// Automatically request login information from the user
		loginUser();
	}
	
	/** Requests login information from the user. Terminates the program after
	 * too many failed login attempts.
	 * 
	 */
	private void loginUser() 
	{
		String text_username,text_password,input1,input2;
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
			
			System.out.println();
			System.out.print("Password: ");
			Scanner pass_input = new Scanner(System.in);
			input2 = user_input.next();
			
				Scanner sc2 = null;
			    try {
			        sc2 = new Scanner(new File("login.txt"));
			        
			    } catch (FileNotFoundException e) {
			        e.printStackTrace();  
			    }
			    while (sc2.hasNextLine()) {
			            Scanner s2 = new Scanner(sc2.nextLine());
			        while (s2.hasNext()) {
			            text_username = s2.next();
			            text_password = s2.next();
			            
			            if(text_username.equals(input1) && text_password.equals(input2))
			            {
			            	System.out.println("Welcome " + text_username + "!");
			            	successful_login = true;
					    	user_input.close();
					    	pass_input.close();
			            }
			            
			        }
			        
			        s2.close();
			    }
			    
		    	sc2.close();
		}		
	}
}
