import java.util.ArrayList;

public class Board {
	
	private ArrayList<List> listArray = new ArrayList<List>();
	private int currentBoardNum;
	
	public void addToListArray(List listInput)
	{
		listArray.add(listInput);
	}
	
	public List getListArrayElement(int index)
	{
		return listArray.get(index);
	}
	
	public int getListArraySize()
	{
		return listArray.size();
	}
	
	public void deleteList(int index)
	{
		listArray.remove(index-1);
	}

	public int getCurrentBoardNum() {
		return currentBoardNum;
	}

	public void setCurrentBoardNum(int currentBoardNum) {
		this.currentBoardNum = currentBoardNum;
	}
}
