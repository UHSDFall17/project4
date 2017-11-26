
public class CorporateUser extends User
{
//	private int placeHolder = 0;	// Unused; left in as part of the equals() example below
	
	public CorporateUser()
	{
		super();
	}
	
	public CorporateUser(int newUserid, String newUserName)
	{
		super(newUserid, newUserName);
	}
	
	public boolean equals(Object obj)
	{
		if (obj instanceof CorporateUser)
		{
			return super.equals(obj);
			
			/* Example of how to handle the equals() method for a subclass when
			 * adding additional fields. */
			
//			return super.equals(obj) 
//					&& placeHolder == ((CorporateUser) obj).placeHolder;
		}
		
		else
		{
			return false;
		}
	}
}
