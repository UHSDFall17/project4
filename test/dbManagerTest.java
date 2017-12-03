import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import org.junit.Test;

public class dbManagerTest 
{
	ByteArrayInputStream in = new ByteArrayInputStream("Hello Hello".getBytes());
	dbManager db = new dbManager();
	Login login = new Login();
	
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
	
//	@Test
//	public void testAddAndDeleteUser()
//	{
//		System.out.println("Adding new user \"Grumpy Cat\" to "
//				+ "database...");
//		//db.addUser("Grumpy Cat", "12345678", false);
//		User testUser = new User("Grumpy Cat", "12345678");
//		db.saveUserToDB(testUser);
//		
//		boolean usernameFound = db.searchForUsername("Grumpy Cat");
//		assertTrue(usernameFound);
//		System.out.println("User found: " + usernameFound);
//		
//		System.out.println("Deleting Grumpy Cat from database...");
//		//db.deleteUser("Grumpy Cat");
//		db.deleteUserFromDB(testUser);
//		
//		usernameFound = db.searchForUsername("Grumpy Cat");
//		assertFalse(usernameFound);
//		System.out.println("User found: " + usernameFound);
//		
//		System.out.println("Test successful\n");
//	}
	
	@Test
	public void testLoadUser()
	{
		int userid = 1;
		int currentBoardNum = 1;
		User testUser = new CorporateUser(userid, "Hello");
		testUser.setPassword("Hello");
		testUser.setCurrentBoard(currentBoardNum);
		
		System.out.println("Loading user \"Hello\" from database...");
		User dbUser = db.loadUser("Hello", "Hello");
		
		System.out.println("Testing loaded user data...");
		assertEquals(testUser, dbUser);
		
		System.out.println("Test successful\n");
	}
	
	@Test
	public void testSaveAndDeleteAndList()
	{
		System.setIn(in);
		
		User user = login.loginUser(db);
		
		int boardID = user.getCurrentBoardNum();
		Board board = new Board();
		board.setCurrentBoardNum(boardID);
		
		db.loadBoardData(board, boardID);
		
		List list = new List("List for Testing");
		board.addToListArray(list);
		db.saveListToDB(user, list);
		
		Card testCard1 = new Card("Test Card 1", "This Card will soon be deleted");
		list.addToCardList(testCard1);
		db.saveCardToDB(list, testCard1);
		
		Card testCard2 = new Card("Test Card 2", "I want to be deleted too");
		list.addToCardList(testCard2);
		db.saveCardToDB(list, testCard2);				
		
		db.deleteListFromDB(list);
		board.deleteList(3);		
		
		System.setIn(System.in);
	}
	
	@Test
	public void testAddModifyDeleteCard()
	{
		System.setIn(in);
		
		User user = login.loginUser(db);
		
		int boardID = user.getCurrentBoardNum();
		Board board = new Board();
		board.setCurrentBoardNum(boardID);
		
		db.loadBoardData(board, boardID);
		
		Card yoyoCard = new Card("Buy a yoyo", "Who doesn't want a yoyo?");
		List list = board.getListArrayElement(0);
		list.addToCardList(yoyoCard);
		db.saveCardToDB(list, yoyoCard);
		
		yoyoCard.setCardTitle("Buy 2 yoyos and a hula hoop");
		db.saveCardToDB(list, yoyoCard);
		
		db.deleteCardFromDB(yoyoCard);
		list.deleteCard(4);
		
		System.setIn(System.in);
	}
}
