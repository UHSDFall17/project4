import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestList {
	@Test
	public void testaddToCardList(){
		Board board = new Board();
		List list = new List();
		Card card = new Card();
		list.addToCardList(card);
		board.addToListArray(list);
		
		assertEquals(board.getListArrayElement(0).getCardListSize(),1);
	}
	@Test
	public void testgetCardListSize(){
		Board board = new Board();
		List list = new List();
		Card card = new Card();
		list.addToCardList(card);
		board.addToListArray(list);
		
		assertEquals(board.getListArrayElement(0).getCardListSize(),1);
	}
	@Test
	public void testgetCardListElement(){
		Board board = new Board();
		List list = new List();
		Card card = new Card();
		list.addToCardList(card);
		board.addToListArray(list);
		
		assertEquals(board.getListArrayElement(0).getCardListElement(0),card);
	}
	@Test
	public void testgetListTitle(){
		List list = new List();
		list.setListTitle("title");
		list.getListTitle();
		assertEquals(list.getListTitle(),"title");
	}
	@Test
	public void testsetListTitle(){
		List list = new List();
		list.setListTitle("title");
		list.getListTitle();
		assertEquals(list.getListTitle(),"title");
	}
	@Test
	public void testgetlistIdNum(){
		List list = new List();
		list.setlistIdNum(1);
		assertEquals(list.getlistIdNum(),1);
	}
	
	@Test
	public void testsetlistIdNum(){
		List list = new List();
		list.setlistIdNum(1);
		assertEquals(list.getlistIdNum(),1);
	}
	

}
