package org.dnyanyog.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	private static String url = "jdbc:mysql://127.0.0.1:3306/e_commerce";
	private static String username = "root";
	private static String password = "Shree@123";
	
	static Connection connection;
	static Statement statement;
	
	public static Connection getConnection() {
		return connection;
	}
	
	public static void setConnection(Connection connection) {
		DBUtil.connection = connection;
	}
	static
	{
		try {
			setConnection(DriverManager.getConnection(url, username, password));
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public static ResultSet resultQuery(String query) {
		try {
			ResultSet result = statement.executeQuery(query);
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Some error occured in DBUtil ResultSet method");
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean executeQuery(String query) throws SQLException{
		getConnection();
		return statement.execute(query);
	}
	
}
