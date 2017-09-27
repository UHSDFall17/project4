import java.util.ArrayList;

public class Board {
	
	private ArrayList<List> listArray = new ArrayList<List>();
	
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
}
