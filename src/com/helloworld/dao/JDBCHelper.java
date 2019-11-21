package com.helloworld.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCHelper {
	
	Connection con=null;
	
	public Connection getConnection() throws ClassNotFoundException {
	String driver ="com.mysql.jdbc.Driver";
	String url ="jdbc:mysql://localhost:3306/webwork";
	String user ="root";
	String password ="duoduo0218";
	
	Class.forName(driver);
	try {
		con=DriverManager.getConnection(url,user,password);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return con;
	}
}
	