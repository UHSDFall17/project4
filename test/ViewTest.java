import static org.junit.Assert.*;

import org.junit.Test;

public class ViewTest {

	View view = new View();
	
//	@Test
//	public void testPrintBoard() 
//	{
//		Card card1 = new Card( "titleOfCard1", "discriptionOfCard1");
//		List list1 = new List("List_Title_blahblah1");
//		list1.addToCardList(card1);
//		Board board1 = new Board();
//		board1.addToListArray(list1);
//		
//		board1.setCurrentBoardNum(1);
//		
//		Controller controller = new Controller();//Controller is called
//		controller.idNumGen(board1);
//
//		view.printBoard(board1);
//	}

	@Test
	public void testPrintBoard() 
	{
		Card card1 = new Card( "titleOfCard1", "discriptionOfCard1");
		Comment comment1 = new Comment("comment1comment1comment1comment1comment1comment1");
		card1.addToCommentList(comment1);
		
		List list1 = new List("List_Title_blahblah1");
		list1.addToCardList(card1);
		
		CheckList check1 = new CheckList();
		card1.addToCheckList(check1);
		card1.setNumVote(3);
		card1.setNumAttachment(1);
		
		Board board1 = new Board();
		board1.addToListArray(list1);
		
		board1.setCurrentBoardNum(1);
		
		Controller controller = new Controller();//Controller is called
		controller.idNumGen(board1);

		view.printBoard(board1);
	}
	
	@Test
	public void testPrintCard()
	{
		Card card1 = new Card( "titleOfCard1", "discriptionOfCard1");
		Comment comment1 = new Comment("comment1comment1comment1comment1comment1comment1");
		card1.addToCommentList(comment1);
		
		List list1 = new List("List_Title_blahblah1");
		list1.addToCardList(card1);
		
		view.printCard(list1);
	}
	
}
