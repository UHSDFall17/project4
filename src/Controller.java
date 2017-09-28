
public class Controller {
	Controller()
	{
		// Request login information from the user
		Login login = new Login();
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
