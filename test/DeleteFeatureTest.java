import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DeleteFeatureTest {

	@Test
	public void testDeleteList() {
		
		Board board = new Board();
		List list = new List();
		board.addToListArray(list);
		board.deleteList(1);
		
		assertEquals(board.getListArraySize(),0);
	}
	
	@Test
	public void testAddCard() {
		
		Board board = new Board();
		List list = new List();
		Card card = new Card();
		board.addToListArray(list);
		board.getListArrayElement(0).addToCardList(card);
		board.getListArrayElement(0).deleteCard(1);
		
		assertEquals(board.getListArraySize(),1);
		assertEquals(board.getListArrayElement(0).getCardListSize(),0);
		
	}
}
