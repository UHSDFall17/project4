/**
 * This class acts as the interface between the program
 * and the database.
 * 
 * These methods should ONLY be called by another class--usually
 * the Controller.
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class dbManager 
{
	/**
	 * Establishes a connection to the database.
	 * 
	 * @return A database connection.
	 */
	private Connection connect()
	{
		Connection conn = null;
		String url = "jdbc:sqlite:project4.db";
		
		try 
		{
			conn = DriverManager.getConnection(url);
		}
		
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
        
		return conn;
    }
	
	/**
	 * Queries the database to determine if a username already exists.
	 * 
	 * @param username The username to search for.
	 * @return True if `username` matches a record in the database,
	 * false otherwise.
	 */
	public boolean searchForUsername(String username)
	{
		boolean usernameFound = false;
		
    	try
		{
    		Connection conn = this.connect();
    		
    		/* Prepare a query statement to search the database for a matching
    		 * username. */
    		
    		String sql = "SELECT * FROM user WHERE username = ?";
    		PreparedStatement pstmt  = conn.prepareStatement(sql);
    				
    		// Pass the parameters into the statement
    		pstmt.setString(1, username);
    		
    		ResultSet rs = pstmt.executeQuery();
    		
    		// Does the result set contain a record?
    		if (rs.next())
    		{
    			usernameFound = true;
    		}
    		
    		conn.close();
    	} 
    	
    	catch (SQLException e) 
    	{
    		System.out.println(e.getMessage());
		}
		
		return usernameFound;
	}
	
	/**
	 * Inserts a new user into the `user` table.
	 * 
	 * @param newUsername The username to insert.
	 * @param newPassword The password to insert.
	 */
	public void addUser(String newUsername, String newPassword)
	{
		try
		{
    		Connection conn = this.connect();
    		
    		/* Prepare a statement to insert a new user into the
    		 * `user` table. */
    		
    		String sql = "INSERT INTO user(username,password)"
    				+ "VALUES(?,?)";
    		PreparedStatement pstmt  = conn.prepareStatement(sql);
    				
    		// Pass the parameters into the statement
    		pstmt.setString(1, newUsername);
    		pstmt.setString(2, newPassword);
    		
    		pstmt.executeUpdate();   		
    		
    		conn.close();
    	} 
    	
    	catch (SQLException e) 
    	{
    		System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Loads data for an existing user.
	 * 
	 * @param username The username to load.
	 * @param password The user's password.
	 * @return A User object if the user exists; null if the database
	 * has no matching user.
	 */
	public User loadUser(String username, String password)
    {
		User user = null;
    	
    	try
		{
    		Connection conn = this.connect();
    		
    		/* Prepare a query statement to search the database for a matching
    		 * username/password pair. */
    		
    		String sql = "SELECT * FROM user WHERE username = ?"
					+ "AND password = ?";
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    				
    		// Pass the parameters into the statement
    		pstmt.setString(1, username);
    		pstmt.setString(2, password);
    		
    		ResultSet rs = pstmt.executeQuery();
    		
    		// attempt to load user data
    		if (rs.next())
    		{
    			int userid = rs.getInt("userid");
    			String userName = rs.getString("username");
    			int currentBoardNum = rs.getInt("current_board");
    			
    			user = new User(userid, userName, currentBoardNum);
    		}
    		
    		conn.close();
    	} 
    	
    	catch (SQLException e) 
    	{
    		System.out.println(e.getMessage());
		}
    	
    	return user;
    }
	
	/**
	 * Deletes a user from the database.
	 * 
	 * @param username The username of the user to be deleted.
	 */
	public void deleteUser(String username)
	{
		try
		{
    		Connection conn = this.connect();
    		
    		/* Prepare a statement to delete a user from the
    		 * `user` table. */
    		
    		String sql = "DELETE FROM user WHERE username = ?";
    		PreparedStatement pstmt  = conn.prepareStatement(sql);
    				
    		// Pass the parameters into the statement
    		pstmt.setString(1, username);
    		
    		pstmt.executeUpdate();   		
    		
    		conn.close();
    	} 
    	
    	catch (SQLException e) 
    	{
    		System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Loads all data associated with a particular Board.
	 * 
	 * @param board The Board object that is currently active.
	 * @param boardID A unique number identifying the board.
	 */
	public void loadBoardData(Board board, int boardID)
	{
		try
		{
			Connection conn = this.connect();
			
			loadLists(conn, board, boardID);
			
			conn.close();
		}
		
    	catch (SQLException e) 
    	{
    		System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Loads all List objects associated with a particular Board.
	 * 
	 * @param conn An active database connection.
	 * @param board The currently active Board object.
	 * @param boardID A unique number identifying the board.
	 * @throws SQLException
	 */
	private void loadLists(Connection conn, Board board, int boardID) throws SQLException
	{
		String sql = "SELECT * FROM list WHERE board_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
				
		// Pass the parameters into the statement
		pstmt.setInt(1, boardID);
		
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next())
		{
			int listIdNum = rs.getInt("l_id");
			String listTitle = rs.getString("list_title");
			
			// Create a List object and add the Cards that belong to it
			List list = new List(listIdNum, listTitle);
			loadCards(conn, list, listIdNum);
			
			board.addToListArray(list);
		}
	}
	
	/**
	 * Loads all Card objects associated with a particular List.
	 * 
	 * @param conn An active database connection.
	 * @param list A List object.
	 * @param listIdNum A unique number identifying the list.
	 * @throws SQLException
	 */
	private void loadCards(Connection conn, List list, int listIdNum) throws SQLException
	{
		String sql = "SELECT * FROM card WHERE list_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		// Pass the parameters into the statement
		pstmt.setInt(1, listIdNum);
		
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next())
		{
			int cardIdNum = rs.getInt("c_id");
			String title = rs.getString("card_title");
			String description = rs.getString("description");
			
			Card card = new Card(cardIdNum, title, description);
			list.addToCardList(card);
		}
	}
}