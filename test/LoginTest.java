import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;

import org.junit.Test;

public class LoginTest 
{
	ByteArrayInputStream in = new ByteArrayInputStream("Hello Hello".getBytes());
	dbManager db = new dbManager();
	Login login = new Login();

	@Test
	public void testLoginUser() 
	{
		System.setIn(in);
		
		/* Test loginUser() with valid credentials.
		 * Test is successful if User is not null. */
		User user = login.loginUser(db);
		assertNotNull(user);
		
		System.setIn(System.in);
	}
}
