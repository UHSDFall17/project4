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
		db.addUser("Grumpy Cat", "12345678");
		
		System.out.println("Searching database for Grumpy Cat...");
		boolean usernameFound = db.searchForUsername("Grumpy Cat");
		assertTrue(usernameFound);
		System.out.println("User found: " + usernameFound);
		
		System.out.println("Deleting Grumpy Cat from database...");
		db.deleteUser("Grumpy Cat");
		
		System.out.println("Searching database for Grumpy Cat...");
		usernameFound = db.searchForUsername("Grumpy Cat");
		assertFalse(usernameFound);
		System.out.println("User found: " + usernameFound);
		
		System.out.println("Test successful\n");
	}
}
