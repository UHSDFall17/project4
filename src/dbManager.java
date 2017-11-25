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
import java.sql.Statement;
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
	 * Saves User data to the database.
	 * 
	 * @param user The User object to save to the database.
	 * @param password The password to save.
	 */
	public void saveUserToDB(User user)
	{
		try
		{
			Connection conn = this.connect();
			
			String sql;
			int userPrimaryKey = user.getUserPrimaryKey();
			String username = user.getUserName();
			String password = user.getPassword();
			
			int is_corporate = 0;
			if (user instanceof CorporateUser)
			{
				is_corporate = 1;
			}
			
			// Is this a new User?
			if (userPrimaryKey == -1)
			{
	    		sql = "INSERT INTO user(username, password, is_corporate)"
	    				+ "VALUES(?,?,?)";
			}
    		
    		// This User already exists
			else
			{
				sql = "UPDATE user SET username = ?, password = ?, is_corporate = ?)"
						+ "WHERE userid = ?";
						
			}
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setInt(3, is_corporate);
			
			/* When updating the table entry for a User that already exists, we need
			 * to include the primary key already assigned to it. */
			if (userPrimaryKey != -1)
			{
				pstmt.setInt(4, userPrimaryKey);
			}
			
			pstmt.executeUpdate();
			
			/* If this is a new User, we need to get the primary key that's just
			 * been assigned to it. Then we update the User object with the new
			 * primary key so we can later update its entry in the database.*/
			if (userPrimaryKey == -1)
			{
				sql = "SELECT last_insert_rowid() AS LAST FROM user";
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				
				int newPrimaryKey = rs.getInt("LAST");
				user.setUserPrimaryKey(newPrimaryKey, this);
				
				// We also need to add a new Board
				addBoardForNewUser(user, conn);				
			}
			
			conn.close();
		}
		
    	catch (SQLException e) 
    	{
    		System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Creates a Board for a new User.
	 * 
	 * @param user The User that needs a new Board.
	 */
	private void addBoardForNewUser(User user, Connection conn) throws SQLException
	{			
		// Create a new Board in the database
		
		int userPrimaryKey = user.getUserPrimaryKey();
		
		String sql = "INSERT INTO board(user_id) VALUES(?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, userPrimaryKey);
		
		pstmt.executeUpdate();
		
		/* Get the primary key for the new Board and store it 
		 * in the User object */
		
		sql = "SELECT last_insert_rowid() AS LAST FROM board";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		int boardPrimaryKey = rs.getInt("LAST");
		user.setCurrentBoard(boardPrimaryKey);
		
		// Update the user table with the current_board id
		
		sql = "UPDATE user SET current_board = ? WHERE userid = ?";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, boardPrimaryKey);
		pstmt.setInt(2, userPrimaryKey);
		
		pstmt.executeUpdate();
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
    			int is_corporate = rs.getInt("is_corporate");
    			
    			if (is_corporate == 1)
    			{
    				user = new CorporateUser(userid, userName);
    				user.setCurrentBoard(currentBoardNum);
    			}
    			
    			else
    			{
    				user = new User(userid, userName);
    				user.setCurrentBoard(currentBoardNum);;
    			}    			
    		}
    		
    		conn.close();
    	} 
    	
    	catch (SQLException e) 
    	{
    		System.out.println(e.getMessage());
		}
    	
    	return user;
    }	
	
	public void deleteUserFromDB(User user)
	{
		try
		{
			Connection conn = this.connect();
			
			int userPrimaryKey = user.getUserPrimaryKey();
			
//			String sql = "DELETE user, board, list, card"
//					+ "FROM user, board, list, card"
//					+ "WHERE user.userid = ?"
//					+ "AND user.userid = board.user_id"
//					+ "AND board.b_id = list.board_id"
//					+ "AND list.l_id = card.list_id";
			
			String sql = "DELETE user, board, list, card"
			+ "FROM user, board, list, card"
			+ "WHERE user.userid = ?"
			+ "AND user.userid = board.user_id,"
			+ "board.b_id = list.board_id,"
			+ "list.l_id = card.list_id";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userPrimaryKey);
			
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
			int cardPrimaryKey = rs.getInt("c_id");
			String title = rs.getString("card_title");
			String description = rs.getString("description");
			
			Card card = new Card(title, description);
			card.setCardPrimaryKey(cardPrimaryKey, this);
			list.addToCardList(card);
		}
	}
	
	/**
	 * Saves the data for a single List to the database.
	 * This does not save data for any Cards the List may be holding.
	 * 
	 * @param user The User that is current logged in.
	 * @param list The List object with data to be saved.
	 */
	public void saveListToDB(User user, List list)
	{
		try
		{
			Connection conn = this.connect();
			
			String sql;
			int listPrimaryKey = list.getListPrimaryKey();
			int board_id = user.getCurrentBoardNum();
			String list_title = list.getListTitle();
			
			// Is this a new List?
			if (listPrimaryKey == -1)
			{
				// Prepare to insert a new row into the database table
				sql = "INSERT INTO list(board_id, list_title) VALUES(?, ?)";
			}
			
			// This List already exists
			else
			{
				// Prepare to update an existing row in the table
				sql = "UPDATE list SET board_id = ?, list_title = ?"
						+ "WHERE l_id = ?";
			}
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			pstmt.setString(2, list_title);
			
			/* When updating the table entry for a List that already exists, we need
			 * to include the primary key already assigned to it. */
			if (listPrimaryKey != -1)
			{
				pstmt.setInt(3, listPrimaryKey);
			}
			
			pstmt.executeUpdate();
			
			/* If this is a new List, we need to get the primary key that's just
			 * been assigned to it. Then we update the List object with the new
			 * primary key so we can later update its entry in the database.*/
			if (listPrimaryKey == -1)
			{
				sql = "SELECT last_insert_rowid() AS LAST FROM list";
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				
				int newPrimaryKey = rs.getInt("LAST");
				list.setListPrimaryKey(newPrimaryKey, this);				
			}
			
			conn.close();
		}
		
    	catch (SQLException e) 
    	{
    		System.out.println(e.getMessage());
		}	
	}
	
	/**
	 * Saves the data for a single Card to the database.
	 * 
	 * @param card The Card object with data to be saved.
	 */
	public void saveCardToDB(List list, Card card)
	{
		try
		{
			Connection conn = this.connect();
			
			String sql;
			int cardPrimaryKey = card.getCardPrimaryKey();
			int list_id = list.getListPrimaryKey();
			String card_title = card.getCardTitle();
			String description = card.getCardDescription();
			
			// Is this a new Card?
			if (cardPrimaryKey == -1)
			{
				// Prepare to insert a new row into the database table
				sql = "INSERT INTO card(list_id, card_title, description)"
						+ "VALUES(?, ?, ?)";
			}
			
			// This Card already exists
			else
			{
				// Prepare to update an existing row in the table
				sql = "UPDATE card SET list_id = ?, card_title = ?,"
						+ "description = ?"
						+ "WHERE c_id = ?";
			}
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, list_id);
			pstmt.setString(2, card_title);
			pstmt.setString(3, description);
			
			/* When updating the table entry for a Card that already exists, we need
			 * to include the primary key already assigned to it. */
			if (cardPrimaryKey != -1)
			{
				pstmt.setInt(4, cardPrimaryKey);
			}
			
			pstmt.executeUpdate();
			
			/* If this is a new Card, we need to get the primary key that's just
			 * been assigned to it. Then we update the Card object with the new
			 * primary key so we can later update its entry in the database.*/
			if (cardPrimaryKey == -1)
			{
				sql = "SELECT last_insert_rowid() AS LAST FROM card";
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				
				int newPrimaryKey = rs.getInt("LAST");
				card.setCardPrimaryKey(newPrimaryKey, this);				
			}
			
			conn.close();
		}
		
    	catch (SQLException e) 
    	{
    		System.out.println(e.getMessage());
		}	
	}
	
	/**
	 * Deletes the data for a single List from the database.
	 * This will also delete data for any Cards associated with
	 * the deleted list.
	 * 
	 * @param list The List object with data to be deleted.
	 */
	public void deleteListFromDB(List list)
	{
		try
		{
			Connection conn = this.connect();
			
			int listPrimaryKey = list.getListPrimaryKey();
			
			// Delete List data from the database
			
			String sql = "DELETE FROM list WHERE l_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, listPrimaryKey);
			
			pstmt.executeUpdate();
			
			// Delete Card data associated with the deleted List
			
			sql = "DELETE FROM card where list_id = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, listPrimaryKey);
			
			pstmt.executeUpdate();
			
			conn.close();
		}
		
    	catch (SQLException e) 
    	{
    		System.out.println(e.getMessage());
		}	
	}
	
	/**
	 * Deletes the data for a single Card from the database.
	 * 
	 * @param card The Card object with data to be deleted.
	 */
	public void deleteCardFromDB(Card card)
	{
		try
		{
			Connection conn = this.connect();
			
			String sql = "DELETE FROM card WHERE c_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, card.getCardPrimaryKey());
			
			pstmt.executeUpdate();
			
			conn.close();
		}
		
    	catch (SQLException e) 
    	{
    		System.out.println(e.getMessage());
		}	
	}
}