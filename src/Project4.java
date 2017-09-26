
public class Project4 
{
	public static void main(String[] args)
	{
		Card card1 = new Card( "tit", "dis","com");
		Card card2 = new Card( "tit2", "dis2","com2");
		List list1 = new List();

		View view = new View();

		list1.addToList(card1);
		list1.addToList(card2);

		view.printCard(list1);
	}
}
