import java.util.Scanner;
//import java.io.*;
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.io.InputStreamReader;

public class Project4 
{
	public static void main(String[] args)
	{
		Scanner user_input = null;
		String user_option = null;
		Comment comment1 = new Comment("comment1comment1comment1comment1comment1comment1");
		Comment comment2 = new Comment("comment2comment2comment2comment2comment2comment2comment2");
		Comment comment3 = new Comment("comment3comment3comment3comment3");
		
		Card card1 = new Card( "titleOfCard1", "discriptionOfCard1");
		card1.addToCommentList(comment1);
		Card card2 = new Card( "titleOfCard2", "discriptionOfCard2");
		card2.addToCommentList(comment2);
		Card card3 = new Card( "titleOfCard3", "discriptionOfCard3");
		card3.addToCommentList(comment3);
		
		List list1 = new List("List_Title_blahblah1");
		List list2 = new List("List_Title_Of_blahblah2");
		
		list1.addToCardList(card1);
		list1.addToCardList(card2);
		list2.addToCardList(card3);
		
		Board board1 = new Board();
		board1.addToListArray(list1);
		board1.addToListArray(list2);
		
		ProjectTitle PTitle = new ProjectTitle();
		PTitle.printTitle();
		
		Controller controller = new Controller();//Controller is called
		controller.createDBmanager();
		
		user_input = new Scanner(System.in);
		user_option = user_input.next();
		
		
		if(user_option.equals("1") || user_option.equals("Login"))
		{	
			User user = controller.loginExistingUser();//Login function is called
			controller.idNumGen(board1);
			
			View view = new View();
			view.printBoard(board1);
			
			/* for database testing
			username: Hello
			password: Hello */
			
			testDatabaseStuff(user, controller, view);
		}
		else if(user_option.equals("2") || user_option.equals("Register"))
		{
			System.out.println("Hello World");
			/*dbManager db = new dbManager();
			
			CreateAccount create = new CreateAccount();
			create.insertAccount(db);*/
			
			
		}
		else if(user_option.equals("3") || user_option.equals("Exit"))
		{
			System.out.println("Goodbye! Terminating program");
			System.exit(0);
			
		}
		
	}
	
	public static void testDatabaseStuff(User user, Controller controller, View view)
	{
		Board dbBoard = new Board();
		int boardID = user.getCurrentBoardNum();
		controller.loadBoardData(dbBoard, boardID);
		controller.idNumGen(dbBoard);
		
		view.printBoard(dbBoard);
		
		System.out.println("Adding new Card...\n");
		
		Card yoyoCard = new Card("Buy a yoyo", "Who doesn't want a yoyo?");
		List list1 = dbBoard.getListArrayElement(0);
		list1.addToCardList(yoyoCard);
		controller.saveCardToDB(list1, yoyoCard);
		controller.idNumGen(dbBoard);
		
		view.printBoard(dbBoard);
		
		System.out.println("Modifying existing Card...\n");
		
		yoyoCard.setCardTitle("Buy 2 yoyos and a hula hoop");
		controller.saveCardToDB(list1, yoyoCard);
		controller.idNumGen(dbBoard);
		
		view.printBoard(dbBoard);
		
		System.out.println("Deleting \"yoyo\" Card...\n");
		
		//int cardIndex = 4;
		//Card toDelete = list1.getCardListElement(4);
		//controller.deleteCard(toDelete);
		//list1.deleteCard(cardIndex);
		controller.deleteCardFromDB(yoyoCard);
		list1.deleteCard(4);
		controller.idNumGen(dbBoard);
		
		view.printBoard(dbBoard);
		
		System.out.println("Adding new List with two Cards...\n");
		
		List list2 = new List("List for Testing");
		dbBoard.addToListArray(list2);
		controller.saveListToDB(user, list2);
		
		Card testCard1 = new Card("Test Card 1", "This Card will soon be deleted");
		list2.addToCardList(testCard1);
		controller.saveCardToDB(list2, testCard1);
		
		Card testCard2 = new Card("Test Card 2", "I want to be deleted too");
		list2.addToCardList(testCard2);
		controller.saveCardToDB(list2, testCard2);
		
		controller.idNumGen(dbBoard);
		
		view.printBoard(dbBoard);
		
		System.out.println("Deleting List...\n");
		
		controller.deleteListFromDB(list2);
		dbBoard.deleteList(3);
		controller.idNumGen(dbBoard);
		
		view.printBoard(dbBoard);
	}
}
