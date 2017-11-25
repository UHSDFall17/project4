import static org.junit.Assert.*;

import org.junit.Test;

public class dbManagerTest 
{
	dbManager db = new dbManager();
	
	@Test
	public void testSearchForUsername() 
	{
		System.out.println("Searching for valid username...");
		boolean usernameFound = db.searchForUsername("Hello");
		assertTrue(usernameFound);
		System.out.println("User found: " + usernameFound);
		System.out.println("Test successful\n");
		
		System.out.println("Searching for invalid username...");
		usernameFound = db.searchForUsername("Ice Cream Sandwich");
		assertFalse(usernameFound);
		System.out.println("User found: " + usernameFound);
		System.out.println("Test successful\n");
	}
	
	@Test
	public void testAddAndDeleteUser()
	{
		System.out.println("Adding new user \"Grumpy Cat\" to "
				+ "database...");
		//db.addUser("Grumpy Cat", "12345678", false);
		User newUser = new User("Grumpy Cat", "12345678");
		db.saveUserToDB(newUser);
		
		boolean usernameFound = db.searchForUsername("Grumpy Cat");
		assertTrue(usernameFound);
		System.out.println("User found: " + usernameFound);
		
		System.out.println("Deleting Grumpy Cat from database...");
		db.deleteUser("Grumpy Cat");
		
		usernameFound = db.searchForUsername("Grumpy Cat");
		assertFalse(usernameFound);
		System.out.println("User found: " + usernameFound);
		
		System.out.println("Test successful\n");
	}
	
	@Test
	public void testLoadUser()
	{
		int userid = 1;
		int currentBoardNum = 1;
		User testUser = new CorporateUser(userid, "Hello");
		testUser.setCurrentBoard(currentBoardNum);
		
		System.out.println("Loading user \"Hello\" from database...");
		User dbUser = db.loadUser("Hello", "Hello");
		
		System.out.println("Testing loaded user data...");
		assertEquals(testUser, dbUser);
		
		System.out.println("Test successful\n");
	}
}
