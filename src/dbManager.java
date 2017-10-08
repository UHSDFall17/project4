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
	 * Queries the database for a username and password.
	 * 
	 * @param username The username to search for.
	 * @param password The password to search for.
	 * @return True if the username and password match a record
	 * in the database; false otherwise.
	 */
	public boolean verifyUser(String username, String password)
    {
		boolean recordFound = false;
    	
    	try
		{
    		Connection conn = this.connect();
    		
    		/* Prepare a query statement to search the database for a matching
    		 * username/password pair. */
    		
    		String sql = "SELECT * FROM user WHERE username = ?"
					+ "AND password = ?";
    		PreparedStatement pstmt  = conn.prepareStatement(sql);
    				
    		// Pass the parameters into the statement
    		pstmt.setString(1, username);
    		pstmt.setString(2, password);
    		
    		ResultSet rs = pstmt.executeQuery();
    		
    		// Does the result set contain a record?
    		if (rs.next())
    		{
    			recordFound = true;
    		}
    		
    		conn.close();
    	} 
    	
    	catch (SQLException e) 
    	{
    		System.out.println(e.getMessage());
		}
    	
    	return recordFound;
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
}