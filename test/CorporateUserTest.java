import static org.junit.Assert.*;

import org.junit.Test;

public class CorporateUserTest {

	@Test
	public void testDefaultConstructor() 
	{
		String testUserName = "no name";
		
		CorporateUser user = new CorporateUser();
		
		assertEquals(testUserName, user.getUserName());
		
	}

	@Test
	public void testEquals()
	{
		CorporateUser user1 = new CorporateUser();
		Card card = new Card();
		
		assertTrue(user1.equals(user1));
		assertFalse(user1.equals(card));
	}
}
