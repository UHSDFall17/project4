import java.util.ArrayList;

public class List 
{
	private String listTitle;
	private int task_num;
	private ArrayList<Card> cardList = new ArrayList<Card>(); 

	public List()
	{
		listTitle = "N/A";
		task_num = 0;
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

	public int getTask_num() {
		return task_num;
	}

	public void setTask_num(int task_num) {

		this.task_num = task_num;

	}



	

	

	

	

}