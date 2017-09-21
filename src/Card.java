
public class Card 
{
	private String title;
	private String description;
	private String comment;
	
	public Card(){
		title = "N/A";
		description = "Insert description here.";
		comment  = "Insert comment here.";
	}
	public void setTitle(String temp_title)
	{
		title = temp_title;
	}
	public String getTitle()
	{
		return title;
	}
	public void setDescription(String temp_description)
	{
		description = temp_description;
	}
	public String getDescription()
	{
		return description;
	}
	public void setComment(String temp_comment)
	{
		comment = temp_comment;
	}
	public String getComment()
	{
		return comment;
	}
}
