import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.Test;

public class AddFeatureTest {

	@Test
	public void testAddList() {
		
		//Controller control = new Controller();
		Board board = new Board();
		List list = new List();
		board.addToListArray(list);
		/*String data = "add list";
		System.setIn(new ByteArrayInputStream(data.getBytes()));

		Scanner scanner = new Scanner(System.in);
		System.out.println(scanner.nextLine());
		control.requestInput(board);
		*/
		assertEquals(board.getListArraySize(),1);
	}
	
	@Test
	public void testAddCard() {
		
		Board board = new Board();
		List list = new List();
		Card card = new Card();
		board.addToListArray(list);
		board.getListArrayElement(0).addToCardList(card);
		assertEquals(board.getListArrayElement(0).getCardListSize(),1);
		
	}
	
	
	
}
