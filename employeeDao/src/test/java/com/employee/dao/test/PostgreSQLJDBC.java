package com.employee.dao.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSQLJDBC {
   private static Connection c = null;
   static {
	   
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/employeedb", "postgres", "postgres");
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	      System.out.println("Opened database successfully");
   }
   public static void main(String args[]) {
//      insertQuery();
	   selectQuery();
//	   updateQuery();
//	   deleteQuery();
   }
   
   public static void insertQuery(){
	   String insertQuery = "INSERT INTO EMPLOYEE.EMPLOYEE_DETAILS (\"empId\",\"empName\",\"empAddress\",\"gender\") VALUES ('5', 'Marry', 'London', 'F' )";
	   try {
		PreparedStatement ps = c.prepareStatement(insertQuery);
		   ps.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}
   }
   
   public static void selectQuery(){
	   String selectQuery = "select * from employee.employee_details";
	   try {
		PreparedStatement ps =  c.prepareStatement(selectQuery);
		   ResultSet rs = ps.executeQuery();
		   while(rs.next()){
		  	 System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4));
		   }
	} catch (SQLException e) {
		e.printStackTrace();
	}
   }
   
   
   public static void updateQuery(){
	   String updateQuery = "update EMPLOYEE.EMPLOYEE_DETAILS set \"empAddress\" = 'London' where \"empId\"=5";
	   try {
		PreparedStatement ps = c.prepareStatement(updateQuery);
		   ps.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}
   }
   
   public static void deleteQuery(){
	   String deleteQuery = "delete from employee.employee_details where \"empId\"=5";
	   try {
		PreparedStatement ps =  c.prepareStatement(deleteQuery);
		ps.executeUpdate();
		   /*ResultSet rs = ps.executeQuery();
		   while(rs.next()){
		  	 System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4));
		   }*/
	} catch (SQLException e) {
		e.printStackTrace();
	}
   }
}
