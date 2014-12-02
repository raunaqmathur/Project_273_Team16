package com.cmpe.project.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataManager {

	public Connection getConnection()
	 {
		//System.out.println("121313");
		 Connection conn =null;
		 
		 
		
			 String url ="jdbc:mysql://localhost:3306/registrationdb";//EmsPropertyUtil.getDBDetails("db.url");
			 String driver="com.mysql.jdbc.Driver";//EmsPropertyUtil.getDBDetails("db.driver");
			 String dbusername="root";//EmsPropertyUtil.getDBDetails("db.username");
			 String dbpassword="password-1";//EmsPropertyUtil.getDBDetails("db.password");
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				//System.out.println("error catch");
				e.printStackTrace();
			}
			 
			 try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/registrationdb","root","password-1");
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			  
		 
		 
		 
		return conn;
	 }
	
	
}
