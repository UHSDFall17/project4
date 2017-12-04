import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
		
		Comment comment1 = new Comment("comment1comment1comment1comment1comment1comment1");
		Comment comment2 = new Comment("comment2comment2comment2comment2comment2comment2comment2");
		
		
		Card card1 = new Card( "titleOfCard1", "discriptionOfCard1");
		card1.addToCommentList(comment1);
		Card card2 = new Card( "titleOfCard2", "discriptionOfCard2");
		card2.addToCommentList(comment2);
		Card card3 = new Card( "titleOfCard3", "discriptionOfCard3");
		
		
		List list1 = new List("List_Title_blahblah1");
		List list2 = new List("List_Title_Of_blahblah2");
		
		CheckList check1 = new CheckList();
		CheckList check2 = new CheckList();
		card1.addToCheckList(check1);
		card1.addToCheckList(check2);
		card3.setNumVote(3);
		card2.setNumAttachment(1);
		list1.addToCardList(card1);
		list1.addToCardList(card2);
		list2.addToCardList(card3);
		
		Board board1 = new Board();
		board1.addToListArray(list1);
		board1.addToListArray(list2);
		
		board1.setCurrentBoardNum(1);
	
		Controller controller = new Controller();//Controller is called
		controller.idNumGen(board1);
		controller.requestInput(board1);
		controller.createDBmanager();
		
		
			//User user = controller.loginExistingUser();//Login function is called
			//controller.idNumGen(board1);
			
			View view = new View();
			view.printBoard(board1);
			Login login = new Login();
			User userTest = new User();
			userTest = login.loginUser(controller.createDBmanager());
			userTest.setCurrentBoard(board1.getCurrentBoardNum());
			
					
			
			/*
			CreateAccount create = new CreateAccount();
			create.insertAccount(db);*/
			
			
		
		
	}
<<<<<<< HEAD

}


=======
}
>>>>>>> branch 'master' of https://github.com/UHSDFall17/project4.git
