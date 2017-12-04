import static org.junit.Assert.*;

import org.junit.Test;

public class CheckListTest {

	@Test
	public void test() 
	{
		String testComment = "test comment";
		
		CheckList comment = new CheckList();	// test default constructor
		comment.setComment("test comment");		
		assertEquals(testComment, comment.getComment());
		
		comment = new CheckList("test comment");	// test other constructor
		assertEquals(testComment, comment.getComment());
	}

}
