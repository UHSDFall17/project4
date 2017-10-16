import java.util.ArrayList;

public class Command {

	private String UserInput;
	private String Command;
	private String SelectName;
	
	public ArrayList<String> processInput(String input){
		ArrayList <String> InputList = new ArrayList<String>();
		
		String delims = "[ ]+";
		String[] tokens = input.split(delims);
		for(int i = 0 ; i < tokens.length; i++)
		{
			InputList.add(tokens[i]);
		}
		
		return InputList;
	}
	
	public String getUserInput() {
		return UserInput;
	}
	
	public void setUserInput(String userInput) {
		UserInput = userInput;
	}
	
	public String getCommand() {
		return Command;
	}
	
	public void setCommand(String command) {
		Command = command;
	}
	
	public String getSelectName() {
		return SelectName;
	}
	
	public void setSelectName(String selectName) {
		SelectName = selectName;
	}
	
	
}
