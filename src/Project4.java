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
		
//		System.out.println("Testing User class...");
//		System.out.println("userid: " + user.getUserid());
//		System.out.println("username: " + user.getUserName());
//		System.out.println("currentBoardNum: " + user.getCurrentBoardNum());
	}
}
