// This class stores data representing the current logged in user.

public class User 
{
	private int userid;				// the user's unique id number
	private String userName;		// the username; should be unique in the database
	private int currentBoardNum;	// the user's current active board
	
	public User()
	{
		userid = -1;
		userName = "no name";
		currentBoardNum = -1;
	}
	
	public User(int newUserid, String newUserName, int newCurrentBoard)
	{
		userid = newUserid;
		userName = newUserName;
		currentBoardNum = newCurrentBoard;
	}
	
	public void setUserid(int newUserid)
	{
		userid = newUserid;
	}
	
	public void setUserName(String newUserName)
	{
		userName = newUserName;
	}
	
	public void setCurrentBoard(int newCurrentBoard)
	{
		currentBoardNum = newCurrentBoard;
	}
	
	public int getUserid()
	{
		return userid;
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public int getCurrentBoardNum()
	{
		return currentBoardNum;
	}
	
	public boolean equals(Object o)
	{
		if (!(o instanceof User))
		{
			return false;
		}
		
		else
		{
			User objUser = (User) o;
			if (userid == objUser.userid
					&& userName.equals(objUser.userName)
					&& currentBoardNum == objUser.currentBoardNum)
			{
				return true;
			}
			
			else
			{
				return false;
			}
		}
	}
}
