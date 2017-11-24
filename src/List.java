import java.util.ArrayList;

public class List 
{
	private String listTitle;
	private int listIdNum;
	private int listPrimaryKey; // used to identify the List in the database
	private ArrayList<Card> cardList = new ArrayList<Card>(); 

	public List()
	{
		listTitle = "N/A";
		listPrimaryKey = -1;
	}	

	public List(String Title)
	{
		this.listTitle = Title;
		listPrimaryKey = -1;
	}
	
	public List(int listIdNum, String listTitle)
	{
		this.listIdNum = listIdNum;
		this.listTitle = listTitle;
		listPrimaryKey = -1;
	}
	
	public void addToCardList(Card cardInput)
	{
		cardList.add(cardInput);
	}

	public int getCardListSize()
	{
		return cardList.size();
	}

	public Card getCardListElement(int index)
	{
		return cardList.get(index);
	}

	public String getListTitle() {
		return listTitle;
	}

	public void setListTitle(String listTitle) {
		this.listTitle = listTitle;
	}

	public int getlistIdNum() {
		return listIdNum;
	}
	
	public int getListPrimaryKey() 
	{
		return listPrimaryKey;
	}

	public void setlistIdNum(int num) {

		this.listIdNum = num;

	}
	
	public void setListPrimaryKey(int listPrimaryKey, dbManager caller)
	{
		if (caller instanceof dbManager)
		{
			this.listPrimaryKey = listPrimaryKey;
		}
		
		else
		{
			System.err.println("Error: caller is not an instance of dbManager");
		}
	}

	public void deleteCard(int index)
	{
		cardList.remove(index-1);
	}

	

	
}