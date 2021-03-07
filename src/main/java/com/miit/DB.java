package com.miit;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
	private static Connection connection = null;
	static{
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/clob", "postgres", "test");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		return connection;
	}
}
