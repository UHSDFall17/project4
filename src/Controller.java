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
	public dbManager createDBmanager()
	{
		db = new dbManager();
		return db;
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
	 * Saves the data for a single User to the database.
	 * 
	 * @param user The User object with data to be saved.
	 */
	public void saveUserToDB(User user)
	{
		db.saveUserToDB(user);
	}
	
	/**
	 * TODO: make this work in the dbManager class
	 * 
	 * @param user
	 */
//	public void deleteUserFromDB(User user)
//	{
//		db.deleteUserFromDB(user);
//	}
//	
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
	
	public void Start()
	{
		ProjectTitle title = new ProjectTitle();
		title.printTitle();
		Scanner user_input = null;
		String user_option = null;
		user_input = new Scanner(System.in);
		user_option = user_input.next();
		
		
		if(user_option.equals("1") || user_option.equals("Login"))
		{	
			
			
		}
		else if(user_option.equals("2") || user_option.equals("Register"))
		{
			
		}
		else if(user_option.equals("3") || user_option.equals("Exit"))
		{
			System.out.println("Goodbye! Terminating program");
			System.exit(0);
			
		}
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
	 * Saves the data for a single List to the database.
	 * This does not save data for any Cards the List may be holding.
	 * 
	 * @param user The User that is current logged in.
	 * @param list The List object with data to be saved.
	 */
	public void saveListToDB(User user, List list)
	{
		db.saveListToDB(user, list);
	}
	
	/**
	 * Deletes the data for a single List from the database.
	 * This will also delete data for any Cards associated with
	 * the deleted list.
	 * 
	 * @param list The List object with data to be deleted.
	 */
	public void deleteListFromDB(List list)
	{
		db.deleteListFromDB(list);
	}
	
	/**
	 * Saves the data for a single Card to the database.
	 * 
	 * @param card The Card object with data to be saved.
	 */
	public void saveCardToDB(List list, Card card)
	{
		db.saveCardToDB(list, card);
	}
	
	/**
	 * Deletes the data for a single Card from the database.
	 * 
	 * @param card The Card object with data to be saved.
	 */
	public void deleteCardFromDB(Card card)
	{
		db.deleteCardFromDB(card);
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
			if(!isInteger(inputList.get(1).substring(4,inputList.get(1).length())))
			{
				return false;
			}
			else if(boardObject.getListArraySize() > Integer.parseInt(inputList.get(1).substring(4)))
			{
				return true;
			}
		}
		else if((inputList.get(1).length() == 4) && (inputList.size() == 3))
		{
			if(!isInteger(inputList.get(2)))
			{
				return false;
			}
			else if(boardObject.getListArraySize() >= Integer.parseInt(inputList.get(2)))
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
			if(!isInteger(inputList.get(1).substring(4,inputList.get(1).length())))
			{
				return false;
			}
			else
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
		}
		else if((inputList.get(1).length()) == 4 && (inputList.size() == 3))
		{
			if(!isInteger(inputList.get(2)))
			{
				return false;
			}
			else
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
		
		//printInputList();
		 
		inputProcessing(boardObject,input,view);
		
		userInput.close();
	}
	
	public void inputProcessing(Board boardObject,String input,View view)
	{
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
			else if((inputList.get(1).substring(0,4).toUpperCase().equals("LIST")) && inListRange(boardObject))
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
				List newList = new List();
				boardObject.addToListArray(newList);
				System.out.println("<< List added >>" );
				idNumGen(boardObject);
				requestInput(boardObject);
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
						invalidCommand(boardObject);
					}
				}
				else if(inputList.size() == 2)
				{
					
				}
				else
				{
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
	}
}
