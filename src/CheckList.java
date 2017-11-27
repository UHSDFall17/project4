
public class CheckList {

	String checkListInput;

	public CheckList(String comment) {
		this.checkListInput = comment;
	}
	
	public CheckList() {
		this.checkListInput = " ";
	}

	public String getComment() {
		return checkListInput;
	}

	public void setComment(String comment) {
		this.checkListInput = comment;
	}
}
