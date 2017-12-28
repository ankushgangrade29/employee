package com.employee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnection {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/employeedb", "postgres", "postgres");
			System.out.println("Connected database successfully");
		} catch (Exception e) {
			System.out.println("Exception while connecting the database");
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return conn;
	}
	
	public static void closePreparedStatement(PreparedStatement ps){
		try {
			if(ps!=null) {
				ps.close();
			}
		}catch(Exception e){
			System.out.println("Exception while closing PreapredStatement");
			e.printStackTrace();
		}
	}
	
	public static void closeResultSet(ResultSet rs){
		try {
			if(rs!=null) {
				rs.close();
			}
		}catch(Exception e){
			System.out.println("Exception while closing ResultSet");
			e.printStackTrace();
		}
	}
	
	public static void closeConnection(Connection conn){
		try {
			if(conn!=null) {
				conn.close();
			}
		}catch(Exception e){
			System.out.println("Exception while closing Connection");
			e.printStackTrace();
		}
	}

}
