import java.util.ArrayList;

public class List 
{
	private String listTitle;
	private int listIdNum;
	private ArrayList<Card> cardList = new ArrayList<Card>(); 

	public List()
	{
		listTitle = "N/A";
	}	

	public List(String Title)
	{
		this.listTitle = Title;
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

	public void setlistIdNum(int num) {

		this.listIdNum = num;

	}



	

	

	

	

}