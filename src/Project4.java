import java.util.Scanner;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Project4 
{
	public static void main(String[] args)
	{

		String text_username,text_password,input1,input2;
		int try_counter = 0;
		boolean successful_login = false;
		
		while(try_counter < 3 && successful_login == false){
			try_counter+=1;
			if(try_counter == 3)
			{
				System.out.println("Too many unsuccessful events! Terminating program.");
			}
			
			System.out.println("Type in your username and password to access your account.");
			System.out.print("Username: ");
			Scanner user_input = new Scanner(System.in); 
			input1 = user_input.next();
			
			System.out.print("Password: ");
			Scanner pass_input = new Scanner(System.in);
			input2 = user_input.next();
			
				Scanner sc2 = null;
			    try {
			        sc2 = new Scanner(new File("login.txt"));
			        
			    } catch (FileNotFoundException e) {
			        e.printStackTrace();  
			    }
			    while (sc2.hasNextLine()) {
			            Scanner s2 = new Scanner(sc2.nextLine());
			        while (s2.hasNext()) {
			            text_username = s2.next();
			            text_password = s2.next();
			            
			            if(text_username.equals(input1) && text_password.equals(input2))
			            {
			            	System.out.println("Welcome " + text_username + "!");
			            	successful_login = true;
			            }
			            
			        }
			    }
		}
		
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
		controller.idNumGen(board1);

		ProjectTitle PTitle = new ProjectTitle();
		PTitle.printTitle();
		View view = new View();
		view.printBoard(board1);
		
	}
}
