import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
	
	private ArrayList<String> inputList = new ArrayList<String>();
	private Board borad = new Board();
	
	private dbManager db;
	
	/**
	 * Creates a new dbManager object.
	 * 
	 */
	public void createDBmanager()
	{
		db = new dbManager();
	}
	
	/** 
	 * Queries the database to determine if a username already exists.
	 * 
	 * @param user
	 * @return True if `username` matches a record in the database,
	 * false otherwise.
	 */
	public boolean searchForUsername(String username)
	{
		return db.searchForUsername(username);
	}
	
	/**
	 * Adds a new user to the database.
	 * 
	 * @param newUsername The username to insert.
	 * @param newPassword The password to insert.
	 */
	public void addUser(String newUsername, String newPassword)
	{
		db.addUser(newUsername, newPassword);
	}
	
	/**
	 * Logs in an existing user.
	 * 
	 */
	public void loginExistingUser()
	{
		// Request login information from the user
		Login login = new Login();
		login.loginUser(db);
	}
	
	// GENERATE A UNIQUE NUMBER FOR CARDS AND LISTS
	public void idNumGen(Board boardInput)
	{
		int cardCounter = 1;
		
		for(int i = 0; i < boardInput.getListArraySize(); i++)
		{
			boardInput.getListArrayElement(i).setlistIdNum(i+1);
			
			for(int y = 0; y < boardInput.getListArrayElement(i).getCardListSize(); y++)
			{
				boardInput.getListArrayElement(i).getCardListElement(y).setCardIdNum(cardCounter);
				cardCounter ++;
			}
		}
	}
	
	/**
	 * Deletes a user from the database.
	 * 
	 * @param username The username of the user to be deleted.
	 */
	public void deleteUser(String username)
	{
		db.deleteUser(username);
	}
	
	public void requestInput()
	{
		String input;
		
		Command command = new Command();
		Scanner userInput = new Scanner(System.in);
		
		System.out.print(">>>");
		input = userInput.next();
		inputList = command.processInput(input);
		
		// input is more then a command and a <name> 
		if(inputList.size() > 2)
		{
			Controller control = new Controller();
			System.out.println("INVALID COMMAND INPUT");
			control.requestInput();
		}
		 
		if(inputList.get(0).equals("Select") || inputList.get(0).equals("SELECT"))
		{
			//make a call to the select function
		}
		
		if(inputList.get(0).equals("Edit") || inputList.get(0).equals("EDIT"))
		{
			//make a call to the edit function
		}
		
		if(inputList.get(0).equals("Delete") || inputList.get(0).equals("DELETE"))
		{
			//make a call to the Delete function
		}
		
		if(inputList.get(0).equals("Add") || inputList.get(0).equals("ADD"))
		{
			//make a call to the add function
		}
		
		if(inputList.get(0).equals("Move") || inputList.get(0).equals("MOVE"))
		{
			//make a call to the move function
		}
		
		userInput.close();
	}
}
