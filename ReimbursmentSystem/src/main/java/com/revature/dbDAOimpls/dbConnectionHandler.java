package com.revature.dbDAOimpls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnectionHandler {
	
	private static final String url = "jdbc:oracle:thin:"+
			"@p1-db-hardin.ct04uaais2l7.us-east-2.rds.amazonaws.com"+
			":1521:ORCL";
	private static final String username = "p1_trey_hardin";
	private static final String password = "190624rev_DB";
	
	public static Connection getConnection() throws SQLException {
		try {
			//System.out.println("Found Driver");
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
        	System.out.println("Did not find Driver");
            e.printStackTrace();
        }
		//THIS WILL BE ON THE TEST AND IN CLIENT INTERVIEWS
		
//		if(DriverManager.getConnection(url, username, password) != null) {
//			System.out.println("there is a connection");
//		}
		
		return DriverManager.getConnection(url, username, password);
	}

}
