//import java.util.Scanner;
//import java.io.*;
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.io.InputStreamReader;

public class Project4 
{
	public static void main(String[] args)
	{
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
		
		Controller controller = new Controller();
		controller.createDBmanager();
		User user = controller.loginExistingUser();
		controller.idNumGen(board1);

		ProjectTitle PTitle = new ProjectTitle();
		PTitle.printTitle();
		View view = new View();
		view.printBoard(board1);
		
		// for database testing
		testDatabaseStuff(user, controller, view);
	}
	
	public static void testDatabaseStuff(User user, Controller controller, View view)
	{
		Board dbBoard = new Board();
		int boardID = user.getCurrentBoardNum();
		controller.loadBoardData(dbBoard, boardID);
		controller.idNumGen(dbBoard);
		
		view.printBoard(dbBoard);
		
		System.out.println("\nAdding new Card...\n");
		
		Card yoyoCard = new Card("Buy a yoyo", "Who doesn't want a yoyo?");
		List list1 = dbBoard.getListArrayElement(0);
		list1.addToCardList(yoyoCard);
		controller.saveCard(list1, yoyoCard);
		controller.idNumGen(dbBoard);
		
		view.printBoard(dbBoard);
		
		System.out.println("Modifying existing Card...\n");
		
		yoyoCard.setCardTitle("Buy 2 yoyos and a hula hoop");
		controller.saveCard(list1, yoyoCard);
		controller.idNumGen(dbBoard);
		
		view.printBoard(dbBoard);
		
		System.out.println("Deleting \"yoyo\" Card...\n");
		
		//int cardIndex = 4;
		//Card toDelete = list1.getCardListElement(4);
		//controller.deleteCard(toDelete);
		//list1.deleteCard(cardIndex);
		controller.deleteCard(yoyoCard);
		list1.deleteCard(4);
		controller.idNumGen(dbBoard);
		
		view.printBoard(dbBoard);
	}
}
