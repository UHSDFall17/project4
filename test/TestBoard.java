import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestBoard {
	@Test
	public void testAddList(){
		Board board = new Board();
		List list = new List();
		board.addToListArray(list);
		assertEquals(board.getListArraySize(),1);
	}
	@Test
	public void testGetListArrayElement(){
		Board board = new Board();
		List list = new List();
		board.addToListArray(list);
		assertEquals(board.getListArraySize(),1);
	}
	@Test
	public void testGetListArraySize(){
		Board board = new Board();
		List list = new List();
		board.addToListArray(list);
		assertEquals(board.getListArraySize(),1);
	}
	@Test
	public void testDeleteList(){
		Board board = new Board();
		List list = new List();
		board.addToListArray(list);
		board.deleteList(1);
		assertEquals(board.getListArraySize(),0);
	}
	@Test
	public void testGetCurrentBoardNum(){
		Board board = new Board();
		board.setCurrentBoardNum(1);
		assertEquals(board.getCurrentBoardNum(),1);

	}
	@Test
	public void testSetCurrentBoardNum(){
		Board board = new Board();
		board.setCurrentBoardNum(1);
		int result = board.getCurrentBoardNum();
		assertEquals(result,1);
	}

}
