// This class stores data representing the current logged in user.

public class User 
{
	private int userPrimaryKey;		// used to identify the user in the database
	private String userName;		// the username; should be unique in the database
	private String password;
	private int currentBoardNum;	// the user's current active board
	
	public User()
	{
		userPrimaryKey = -1;
		userName = "no name";
		currentBoardNum = -1;
	}
	
	public User(int newUserid, String newUserName)
	{
		userPrimaryKey = newUserid;
		userName = newUserName;
		currentBoardNum = -1;
	}
	
	public User(String newUserName, String newPassword)
	{
		userPrimaryKey = -1;
		userName = newUserName;
		password = newPassword;
		currentBoardNum = -1;
	}
	
	public void setUserPrimaryKey(int newUserid, dbManager caller)
	{
		userPrimaryKey = newUserid;
	}	
	
	public void setUserName(String newUserName)
	{
		userName = newUserName;
	}
	
	public void setPassword(String newPassword)
	{
		password = newPassword;
	}
	
	public void setCurrentBoard(int newCurrentBoard)
	{
		currentBoardNum = newCurrentBoard;
	}
	
	public int getUserPrimaryKey()
	{
		return userPrimaryKey;
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public int getCurrentBoardNum()
	{
		return currentBoardNum;
	}
	
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		User objUser = (User) obj;
					
		return userPrimaryKey == objUser.userPrimaryKey
				&& userName.equals(objUser.userName)
				&& password.equals(objUser.password)
				&& currentBoardNum == objUser.currentBoardNum;		
	}
}
