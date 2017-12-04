import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class TestCommand {
	@Test
	public void testprocessInput(){
		ArrayList <String> InputList = new ArrayList<String>();
		InputList.add("test");
		Command cmd = new Command();
		assertEquals(cmd.processInput("test"),InputList);
	}
	@Test
	public void testgetUserInput(){
		
		Command cmd = new Command();
		cmd.setUserInput("test");
		assertEquals(cmd.getUserInput(),"test");
	}
	@Test
	public void testsetUserInput(){
		Command cmd = new Command();
		cmd.setUserInput("test");
		assertEquals(cmd.getUserInput(),"test");
	}
	@Test
	public void testgetCommand(){
		
		Command cmd = new Command();
		cmd.setCommand("test");
		assertEquals(cmd.getCommand(),"test");
	}
	@Test
	public void testsetCommand(){
		Command cmd = new Command();
		cmd.setCommand("test");
		assertEquals(cmd.getCommand(),"test");
	}
	@Test
	public void testgetSelectName(){
		Command cmd = new Command();
		cmd.setSelectName("test");
		assertEquals(cmd.getSelectName(),"test");
	}
	@Test
	public void testsetSelectName(){
		Command cmd = new Command();
		cmd.setSelectName("test");
		assertEquals(cmd.getSelectName(),"test");
	}
}
