
public class Controller {
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

	
}
