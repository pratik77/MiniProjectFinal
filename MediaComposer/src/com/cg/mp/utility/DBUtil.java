package com.cg.mp.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBUtil {

	private static Connection connStudent;
	
	public static Connection createConnection() throws SQLException
	{
		
		ResourceBundle resOracle=ResourceBundle.getBundle("oracle2");
		String url=resOracle.getString("url");
		String username=resOracle.getString("username");
		String password=resOracle.getString("password");
		
		
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		connStudent=DriverManager.getConnection(url,username,password);
		return connStudent;
	}
	
	public static void closeConnection() throws SQLException
	{
		if(connStudent!=null)
			connStudent.close();
	}
}
