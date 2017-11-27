public class View {
	
	// PRINT ALL OBJECTS IN A BOARD 
	public void printBoard(Board boardInput)
	{
		for(int i = 0; i < boardInput.getListArraySize(); i++)
		{
			System.out.println("List " + boardInput.getListArrayElement(i).getlistIdNum() + ": " + boardInput.getListArrayElement(i).getListTitle());
			
			for(int y = 0; y < boardInput.getListArrayElement(i).getCardListSize(); y++)
			{
				System.out.println("   Card " + boardInput.getListArrayElement(i).getCardListElement(y).getCardIdNum() + ": " + boardInput.getListArrayElement(i).getCardListElement(y).getCardTitle());
				
				
				if(!boardInput.getListArrayElement(i).getCardListElement(y).getCardDescription().equals("No description."))
				{
					System.out.println("             * description");
				}
				if (!(boardInput.getListArrayElement(i).getCardListElement(y).getNumVote() == 0))
				{
					System.out.println("             * " + boardInput.getListArrayElement(i).getCardListElement(y).getNumVote() + " votes");
				}
				if (!(boardInput.getListArrayElement(i).getCardListElement(y).getNumAttachment() == 0))
				{
					System.out.println("             * " + boardInput.getListArrayElement(i).getCardListElement(y).getNumAttachment() + " attachment");
				}
				if (!(boardInput.getListArrayElement(i).getCardListElement(y).getCommentListSize() == 0))
				{
					System.out.println("             * " + boardInput.getListArrayElement(i).getCardListElement(y).getCommentListSize() + " comment");
				}
				if (!(boardInput.getListArrayElement(i).getCardListElement(y).getCheckListSize() == 0))
				{
					System.out.println("             * (" + boardInput.getListArrayElement(i).getCardListElement(y).getCheckListFinishSize() + "/" + boardInput.getListArrayElement(i).getCardListElement(y).getCheckListSize() + ") check list");
				}
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
