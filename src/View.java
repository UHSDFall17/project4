public class View {



	public void printCard(List listInput){

		

		for( int i = 0; i < listInput.getCardListSize(); i++ ){

			System.out.println(listInput.getCardListElement(i).getTitle());

			System.out.println(listInput.getCardListElement(i).getDescription());

			System.out.println(listInput.getCardListElement(i).getComment());		

		}

		

	}

	

}
