package com.employee.dao.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.postgresql.osgi.PGDataSourceFactory;
import org.postgresql.xa.PGXADataSource;

public class PGDSConnection {
	public void getConnection(){
		try {
			PGXADataSource xa = new PGXADataSource();
			xa.setDatabaseName("employeedb");
			xa.setUrl("jdbc:postgresql://localhost:5432/employeedb");
			xa.setUser("postgres");
			xa.setPassword("postgres");
			
			System.out.println(xa.getConnection());
			Connection c=xa.getConnection();
			String selectQuery = "select * from employee.employee_details where \"empId\"=?";
			   try {
				PreparedStatement ps =  c.prepareStatement(selectQuery);
				ps.setInt(1, 3);
				   ResultSet rs = ps.executeQuery();
				   while(rs.next()){
				  	 System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4));
				   }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		PGDSConnection ds = new PGDSConnection();
		ds.getConnection();
	}
}
