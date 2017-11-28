import java.util.ArrayList;

public class Card 
{
	private String title;
	private String description;
	private int cardIdNum;			// id number to display in view for the user
	private int cardPrimaryKey;		// used to identify the card in the database
	private ArrayList<Comment> commentList = new ArrayList<Comment>(); 
	private ArrayList<CheckList> checkList = new ArrayList<CheckList>(); 
	private ArrayList<Integer> checkListFinish = new ArrayList<Integer>(); 
	
	private int numVote;
	private int numAttachment;
	
	
	public Card(String title, String description, Comment commentValue) 
	{
		this.title = title;
		this.description = description;
		cardPrimaryKey = -1;
		this.addToCommentList(commentValue);
		numVote = 0;
		numAttachment = 0;
	}
	
	public Card(String title, String description)
	{
		this.title = title;
		this.description = description;
		cardPrimaryKey = -1;
		numVote = 0;
		numAttachment = 0;
	}
	
	public Card()
	{
		title = "No title";
		description = "No description.";
		cardPrimaryKey = -1;
		numVote = 0;
		numAttachment = 0;
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
	
	public CheckList getCheckListElement(int index)
	{
		return checkList.get(index);
	}
	
	public void addToCheckList(CheckList checkListInput)
	{
		checkList.add(checkListInput);
	}

	public int getCheckListSize()
	{
		return checkList.size();
	}

	
	public int getCheckListFinishElement(int index)
	{
		return checkListFinish.get(index);
	}
	
	public void addToCheckListFinish(int commentInput)
	{
		checkListFinish.add(commentInput);
	}

	public int getCheckListFinishSize()
	{
		return checkListFinish.size();
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
	
	public void setCardPrimaryKey(int cardPrimaryKey, dbManager caller)
	{
		if (caller instanceof dbManager)
		{
			this.cardPrimaryKey = cardPrimaryKey;
		}
		
		else
		{
			System.err.println("Error: caller is not an instance of dbManager");
		}
	}

	public int getNumVote() {
		return numVote;
	}

	public void setNumVote(int numVote) {
		this.numVote = numVote;
	}

	public int getNumAttachment() {
		return numAttachment;
	}

	public void setNumAttachment(int numAttachment) {
		this.numAttachment = numAttachment;
	}
	public void addVote()
	{
		numVote = numVote + 1;
	}
}
