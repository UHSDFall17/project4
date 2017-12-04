import static org.junit.Assert.*;

import org.junit.Test;

public class CommentTest {

	@Test
	public void test() 
	{
		String testComment = "test comment";
		
		Comment comment = new Comment();	// test default constructor
		comment.setComment("test comment");		
		assertEquals(testComment, comment.getComment());
		
		comment = new Comment("test comment");	// test other constructor
		assertEquals(testComment, comment.getComment());
	}

}
