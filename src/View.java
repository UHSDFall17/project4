public class View {

	public void printBoard(Board boardInput)
	{
		for(int i = 0; i < boardInput.getListArraySize(); i++)
		{
			System.out.println("List " + boardInput.getListArrayElement(i).getlistIdNum() + ": " + boardInput.getListArrayElement(i).getListTitle());
			
			for(int y = 0; y < boardInput.getListArrayElement(i).getCardListSize(); y++)
			{
				System.out.println("   Card " + boardInput.getListArrayElement(i).getCardListElement(y).getCardIdNum() + ": " + boardInput.getListArrayElement(i).getCardListElement(y).getCardTitle());
				System.out.println("         " +  "  " + boardInput.getListArrayElement(i).getCardListElement(y).getCardDescription());
				
				if((boardInput.getListArrayElement(i).getCardListSize()-1) == y)
				{
					System.out.println();
				}
			}
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
