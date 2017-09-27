public class View {

	public void printBoard(Board boardInput)
	{
		for(int i = 0; i<boardInput.getListArraySize(); i++)
		{
			System.out.println("List " + (i+1) + ": " + boardInput.getListArrayElement(i).getListTitle() + "\n");
			
		}
	}
	
	public void printCard(List listInput)
	{	
		for( int i = 0; i < listInput.getCardListSize(); i++ ){
			System.out.println(listInput.getCardListElement(i).getCardTitle());
			System.out.println(listInput.getCardListElement(i).getCardDescription());
			System.out.println(listInput.getCardListElement(i).getCommentListElement(0).getComment());		
		}
	}

	

}
