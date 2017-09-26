import java.util.ArrayList;
public class List 
{
	private String name;
	private int index; // this is used to initially store into the card_list array and to keep track on the array's size 
	private int task_num;
	private ArrayList<Card> cardList = new ArrayList<Card>(); 
	
	public List()
	{
		name = "N/A";
		index = 0;
		task_num = 0;
	}	

	public void addToList(Card cardInput)
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
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTask_num() {
		return task_num;
	}

	public void setTask_num(int task_num) {
		this.task_num = task_num;
	}

	
	
	
	
}
