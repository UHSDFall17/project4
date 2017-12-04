import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

	@Test
	public void testDefaultConstructor() 
	{
		int defaultUserPrimaryKey = -1;
		String defaultUserName = "no name";
		int defaultCurrentBoardNum = -1;
		
		User user = new User();
		
		assertEquals(defaultUserPrimaryKey, user.getUserPrimaryKey());
		assertEquals(defaultUserName, user.getUserName());
		assertEquals(defaultCurrentBoardNum, user.getCurrentBoardNum());
	}

	@Test
	public void testConstructor2()
	{
		int testUserPrimaryKey = -1;
		String testUserName = "Yoda";
		String testPassword = "cookies";
		int testCurrentBoardNum = -1;
		
		User user = new User("Yoda", "cookies");
		
		assertEquals(testUserPrimaryKey, user.getUserPrimaryKey());
		assertEquals(testUserName, user.getUserName());
		assertEquals(testPassword, user.getPassword());
		assertEquals(testCurrentBoardNum, user.getCurrentBoardNum());
	}
	
	@Test
	public void testMutatorsAndAccessors()
	{				
		int testUserPrimaryKey = 12;		
		String testUserName = "Angus";		
		String testPassword = "12345678";
		int testCurrentBoardNum = 6;
		
		dbManager db = new dbManager();
		User user = new User();
		
		user.setUserPrimaryKey(12, db);
		user.setUserName("Angus");
		user.setPassword("12345678");
		user.setCurrentBoard(6);
		
		assertEquals(testUserPrimaryKey, user.getUserPrimaryKey());
		assertEquals(testUserName, user.getUserName());
		assertEquals(testPassword, user.getPassword());
		assertEquals(testCurrentBoardNum, user.getCurrentBoardNum());
	}
	
	@Test
	public void testEquals()
	{
		User nullRef = null;
		User defaultUser1 = new User();
		User fancyUser1 = new User("Fancy User", "fancypassword");
		User fancyUser2 = new User("Fancy User", "fancypassword");
		Card card = new Card();
		
		assertTrue(defaultUser1.equals(defaultUser1));			
		assertFalse(defaultUser1.equals(nullRef));
		assertFalse(defaultUser1.equals(card));
		
		assertFalse(defaultUser1.equals(fancyUser1));
		assertTrue(fancyUser1.equals(fancyUser2));
	}
}
