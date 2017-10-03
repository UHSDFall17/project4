/**
 * This class acts as the interface between the program
 * and the database.
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
}