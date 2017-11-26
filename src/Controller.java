import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
	
	private int destNum;
	
	private ArrayList<String> inputList = new ArrayList<String>();
	private Board board = new Board();
	
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
	public User loginExistingUser()
	{
		// Request login information from the user
		Login login = new Login();
		User user = login.loginUser(db);
		
		return user;
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
	
	/**
	 * Loads all List and Card data for the current Board.
	 * 
	 * @param board The currently active Board object.
	 * @param boardID A unique number identifying the board.
	 */
	public void loadBoardData(Board board, int boardID)
	{
		db.loadBoardData(board, boardID);
	}
	
	/**
	 * Saves the data for a single Card to the database.
	 * 
	 * @param card The Card object with data to be saved.
	 */
	public void saveCard(List list, Card card)
	{
		db.saveCard(list, card);
	}
	
	/**
	 * Deletes the data for a single Card from the database.
	 * 
	 * @param card The Card object with data to be saved.
	 */
	public void deleteCard(Card card)
	{
		db.deleteCard(card);
	}
	
	public void invalidCommand(Board bor)
	{
		System.out.println("INVALID COMMAND INPUT. PLEASE TRY AGAIN. IF YOU NEED HELP, TYPE \"HELP\".");
		requestInput(bor);
	}
	
	public void printInputList()
	{
		for(int i = 0; i < inputList.size(); i++ )
		{
			System.out.println(inputList.get(i));
		}
	}
	
	public int getListPos(Board boardObject)
	{
		int temp = 0; 
		
		if(inputList.get(1).length() > 4)
		{
			for(int i = 0; i < boardObject.getListArraySize() ; i++ )
			{
				temp += boardObject.getListArrayElement(i).getCardListSize();
				if(boardObject.getListArrayElement(i).getCardListSize() < Integer.parseInt(inputList.get(1).substring(4)))
						{
							return i;
						}
			}
		}
		
		if(inputList.get(1).length() == 4)
		{
			for(int i = 0; i < boardObject.getListArraySize() ; i++ )
			{
				temp += boardObject.getListArrayElement(i).getCardListSize();
				if(boardObject.getListArrayElement(i).getCardListSize() < Integer.parseInt(inputList.get(2)))
						{
							return i;
						}
			}
		}
		return -1;
	}
	
	public int getCardPos(Board boardObject, int listPos)
	{
		int temp = 0;
		
		if(inputList.get(1).length() > 4)
		{
			temp = Integer.parseInt(inputList.get(1).substring(4));
			for(int i = 0; i < listPos; i++)
			{
				temp -= boardObject.getListArrayElement(i).getCardListSize();
			}
			
			return temp;
		}
		else if(inputList.get(1).length() == 4)
		{
			temp = Integer.parseInt(inputList.get(2));
			for(int i = 0; i < listPos; i++)
			{
				temp -= boardObject.getListArrayElement(i).getCardListSize();
			}
			
			return temp;
		}
		return -1;
	}
	
	public boolean inListRange(Board boardObject)
	{
		if(inputList.get(1).length() > 4)
		{
			if(boardObject.getListArraySize() > Integer.parseInt(inputList.get(1).substring(4)))
			{
				return true;
			}
		}
		else if(inputList.get(1).length() == 4)
		{
			if(boardObject.getListArraySize() >= Integer.parseInt(inputList.get(2)))
			{
				return true;
			}
		}
		
			return false;
		
	}
	
	public boolean inCardRange(Board boardObject)
	{
		int temp = 0;
		
		if(inputList.get(1).length() > 4)
		{	
			for(int i = 0; i < boardObject.getListArraySize(); i++)
			{
				temp += boardObject.getListArrayElement(i).getCardListSize();
				if(temp > Integer.parseInt(inputList.get(1).substring(4)))
						{
							return true;
						}
			}
		}
		else if(inputList.get(1).length() == 4)
		{
			for(int i = 0; i < boardObject.getListArraySize(); i++)
			{
				temp += boardObject.getListArrayElement(i).getCardListSize();
				if(temp > Integer.parseInt(inputList.get(2)))
						{
							return true;
						}
			}
		}
		
			return false;
		
	}
	
	public static boolean isInteger(String s) {
	    return isInteger(s,10);
	}

	public static boolean isInteger(String s, int radix) {
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(s.charAt(i),radix) < 0) return false;
	    }
	    return true;
	}
	
	public void requestInput(Board boardObject)
	{
		String input;
		
		View view = new View();
		
		Command command = new Command();
		Scanner userInput = new Scanner(System.in);
		
		System.out.print(">>>");
		input = userInput.nextLine();
		inputList = command.processInput(input);
		
		printInputList();
		 
		if(inputList.get(0).equals("select") || inputList.get(0).equals("Select") || inputList.get(0).equals("SELECT"))
		{
			//make a call to the select function
		}
		
		else if(inputList.get(0).equals("edit") || inputList.get(0).equals("Edit") || inputList.get(0).equals("EDIT"))
		{
			//make a call to the edit function
		}
		
		else if(inputList.get(0).toUpperCase().equals("DELETE"))
		{
			//make a call to the Delete function
			if(inputList.get(1).toUpperCase().equals("BOARD"))
			{
				
			}
			else if((inputList.get(1).substring(0,4).toUpperCase().equals("LIST")) && inListRange(boardObject) )
			{
				
				if(inputList.get(1).length() > 4)
				{
					if(inputList.size() > 3)
					{
						invalidCommand(boardObject);
					}
					else
					{
						boardObject.deleteList(Integer.parseInt(inputList.get(1).substring(4)));
						System.out.println("List deleted");
						idNumGen(boardObject);
						requestInput(boardObject);
					}
				}
				
				else if(inputList.get(1).length() == 4)
				{
					if(inputList.size() > 3)
					{
						invalidCommand(boardObject);
					}
					else
					{
						boardObject.deleteList(Integer.parseInt(inputList.get(2)));
						System.out.println("<< List deleted >>");
						idNumGen(boardObject);
						requestInput(boardObject);
					}
				}
			}
			else if((inputList.get(1).substring(0,4).toUpperCase().equals("CARD")) && inCardRange(boardObject))
			{
				
				if(inputList.get(1).length() > 4)
				{
					if(inputList.size() > 3)
					{
						invalidCommand(boardObject);
					}
					else
					{
						boardObject.getListArrayElement(getListPos(boardObject)+1).deleteCard(getCardPos(boardObject,getListPos(boardObject))-1);
						System.out.println("<< Card deleted >>");
						idNumGen(boardObject);
						requestInput(boardObject);
					}
				}
				
				else if(inputList.get(1).length() == 4)
				{
					if(inputList.size() > 3)
					{
						invalidCommand(boardObject);
					}
					else
					{
						boardObject.getListArrayElement(getListPos(boardObject)-1).deleteCard(getCardPos(boardObject,getListPos(boardObject))+1);
						System.out.println("<< Card deleted >>");
						idNumGen(boardObject);
						requestInput(boardObject);
					}
				}
			}
			else if(inputList.get(1).toUpperCase().equals("COMMENT"))
			{
				
			}
			
			else 
			{
				invalidCommand(boardObject);
			}
		}
		
		else if(inputList.get(0).toUpperCase().equals("ADD"))
		{
			//make a call to the add function
			if(inputList.get(1).toUpperCase().equals("LIST"))
			{
				
			}
			else if(inputList.get(1).toUpperCase().equals("CARD"))
			{
				if((inputList.get(2).toUpperCase().equals("TO")) && (inputList.get(3).toUpperCase().substring(0,4).equals("LIST")))
				{
					if((inputList.size() == 5) && isInteger(inputList.get(4)))
					{
						Card newCard = new Card();
						boardObject.getListArrayElement(Integer.parseInt(inputList.get(4))-1).addToCardList(newCard);
						System.out.println("<< Card added to list " + inputList.get(4) + " >>" );
						idNumGen(boardObject);
						requestInput(boardObject);
					}
					else if((inputList.size() == 4) && isInteger(inputList.get(3).substring(4,5)))
					{
						Card newCard = new Card();
						boardObject.getListArrayElement(Integer.parseInt(inputList.get(3).substring(4,5))-1).addToCardList(newCard);
						System.out.println("<< Card added to list " + inputList.get(3).substring(4,5) + " >>" );
						idNumGen(boardObject);
						requestInput(boardObject);
					}
					
					else
					{
						System.out.println("ee");
						invalidCommand(boardObject);
					}
				}
				else if(inputList.size() == 2)
				{
					
				}
				else
				{
					System.out.println("eeee");
					invalidCommand(boardObject);
				}
			}
		}
		
		
		else if(inputList.get(0).toUpperCase().equals("VIEW"))
		{
			//Prints current board
			if(inputList.size() == 1)
			{
					view.printBoard(boardObject);
					requestInput(boardObject);
			}
			
			else
				{
					invalidCommand(boardObject);
				}
		}

		else
		{
			System.out.println("error1");
			invalidCommand(boardObject);
		}
		
		userInput.close();
	}
}
