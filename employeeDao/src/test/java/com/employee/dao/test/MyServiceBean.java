package com.employee.dao.test;

import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import javax.naming.spi.InitialContextFactoryBuilder;
import javax.naming.spi.NamingManager;
import javax.sql.DataSource;

public class  MyServiceBean {

    public void startProcess() {
    
        try {
			DataSource ds = (DataSource) new InitialContext().lookup("PostgresDS");
			Connection con = ds.getConnection();

//			Connection c=xa.getConnection();
			String selectQuery = "select * from employee.employee_details where \"empId\"=?";
			   try {
				PreparedStatement ps =  con.prepareStatement(selectQuery);
				ps.setInt(1, 3);
				   ResultSet rs = ps.executeQuery();
				   while(rs.next()){
				  	 System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4));
				   }
			} catch (SQLException e) {
				e.printStackTrace();
			}
			   
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
        
    }    
 /*   public static void main(String[] args) {        
        MyServiceBean b = new MyServiceBean();
        b.startProcess();              
    }*/
    
    public static void main(String[] args) {
    	 final class LocalDataSource implements DataSource , Serializable {

             private String connectionString;
             private String username;
             private String password;
             
             LocalDataSource(String connectionString, String username, String password) {
                 this.connectionString = connectionString;
                 this.username = username;
                 this.password = password;
             }
             
             public Connection getConnection() throws SQLException
             {
                 return DriverManager.getConnection(connectionString, username, password);
             }
     
             public Connection getConnection(String arg0, String arg1)
                     throws SQLException
             {
                 return getConnection();              
             }
     
             public PrintWriter getLogWriter() throws SQLException
             {
                 return null;
             }
     
             public int getLoginTimeout() throws SQLException
             {
                 return 0;
             }
     
             public void setLogWriter(PrintWriter out) throws SQLException {}
     
             public void setLoginTimeout(int seconds) throws SQLException {}

			@Override
			public Logger getParentLogger()
					throws SQLFeatureNotSupportedException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <T> T unwrap(Class<T> iface) throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean isWrapperFor(Class<?> iface) throws SQLException {
				// TODO Auto-generated method stub
				return false;
			}

     }
     
     final class DatabaseContext extends InitialContext {

         DatabaseContext() throws NamingException {}

         @Override
         public Object lookup(String name) throws NamingException
         {
             try {
                 //our connection strings
                 Class.forName("org.postgresql.Driver");
                 DataSource ds1 = new LocalDataSource("jdbc:postgresql://localhost:5432/employeedb", "postgres", "postgres");
//                 DataSource ds2 = new LocalDataSource("jdbc:mysql://dbserver1/dboneB", "username", "xxxpass");

                 Properties prop = new Properties();
                 prop.put("PostgresDS", ds1);
//                 prop.put("jdbc/ds2", ds2);
                 
                 Object value = prop.get(name);
                 return (value != null) ? value : super.lookup(name);
             }
              catch(Exception e) {
                  System.err.println("Lookup Problem " + e.getMessage());
                  e.printStackTrace();
              }  
              return null;            
         }        
         
     }

     final class DatabaseContextFactory implements  InitialContextFactory, InitialContextFactoryBuilder {

         public Context getInitialContext(Hashtable<?, ?> environment)
                 throws NamingException
         {
             return new DatabaseContext();
         }

         public InitialContextFactory createInitialContextFactory(
                 Hashtable<?, ?> environment) throws NamingException
         {
             return new DatabaseContextFactory();
         }
         
     }
     
     try {
		NamingManager.setInitialContextFactoryBuilder(new DatabaseContextFactory());
	} catch (NamingException e) {
		e.printStackTrace();
	}   

     MyServiceBean b = new MyServiceBean();
     b.startProcess();
	}
}