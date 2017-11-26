import java.util.ArrayList;

public class Card 
{
	private String title;
	private String description;
	private int cardIdNum;			// id number to display in view for the user
	private int cardPrimaryKey;		// used to identify the card in the database
	private ArrayList<Comment> commentList = new ArrayList<Comment>(); 
	
	public Card(String title, String description, Comment commentValue) 
	{
		this.title = title;
		this.description = description;
		cardPrimaryKey = -1;
		this.addToCommentList(commentValue);
	}
	
	public Card(String title, String description)
	{
		this.title = title;
		this.description = description;
		cardPrimaryKey = -1;
	}
	
	public Card()
	{
		title = "N/A";
		description = "No description.";
		cardPrimaryKey = -1;
	}
	
	public void addToCommentList(Comment commentInput)
	{
		commentList.add(commentInput);
	}

	public int getCommentListSize()
	{
		return commentList.size();
	}

	public Comment getCommentListElement(int index)
	{
		return commentList.get(index);
	}
	
	public void setCardTitle(String temp_title)
	{
		title = temp_title;
	}
	public String getCardTitle()
	{
		return title;
	}
	public void setCardDescription(String temp_description)
	{
		description = temp_description;
	}
	public String getCardDescription()
	{
		return description;
	}

	public int getCardIdNum() {
		return cardIdNum;
	}

	public void setCardIdNum(int cardIdNum) {
		this.cardIdNum = cardIdNum;
	}
	
	public int getCardPrimaryKey()
	{
		return cardPrimaryKey;
	}
	
	public void setCardPrimaryKey(int cardPrimaryKey)
	{
		this.cardPrimaryKey = cardPrimaryKey;
	}
}
