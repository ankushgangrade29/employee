package com.employee.dao.test;

import java.sql.Connection;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import javax.naming.spi.InitialContextFactoryBuilder;
import javax.naming.spi.NamingManager;
import javax.sql.DataSource;

import org.postgresql.ds.PGConnectionPoolDataSource;

public class InitContDSConnection {
	private static final String INITIAL_CONTEXT_FACTORY = "org.jnp.interfaces.NamingContextFactory";
	private static final String URL_PKG_PREFIXES = "org.jboss.naming:org.jnp.interfaces:org.jboss.naming:org.jnp.interfaces";
	
	private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/employeedb";
	private static final String JNDI_SUBCONTEXT1 = "java:jboss";
	private static final String JNDI_SUBCONTEXT2 = "java:jboss/datasources";
	public static final String JNDI_DATASOURCE_URL = "java:jboss/datasources/PostgresDS";

//	public static final String DATABASE_INIT_SCRIPT = "TestDatabaseScript.SQL";
	
	public void getConnection(){
		try {
			Properties props = new Properties();
			props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			props.setProperty(Context.PROVIDER_URL, "http-remoting://localhost:8080");
			props.setProperty(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.remote.interface");
			props.setProperty(Context.SECURITY_PRINCIPAL, "postgres");
			props.setProperty(Context.SECURITY_AUTHENTICATION, "postgres");
			
		/*	PGConnectionPoolDataSource pool = new PGConnectionPoolDataSource();
			pool.setUrl("jdbc:postgresql://localhost:5432/employeedb");
			pool.setUser("postgres");
			pool.setPassword("postgres");*/
			
			
             DataSource ds = (DataSource) new InitialContext().lookup("PostgresDS");
 			Connection con = ds.getConnection();
//			props.setProperty(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
//			props.setProperty(Context.PROVIDER_URL, "http-remoting://127.0.0.1:9990");
//			props.setProperty(Context.URL_PKG_PREFIXES, URL_PKG_PREFIXES);
//			props.setProperty(Context.SECURITY_PRINCIPAL, "wildflyuser");
//			props.setProperty(Context.SECURITY_AUTHENTICATION, "adminpass");
			
			
			
			
			
			/*InitialContext con = new InitialContext(props);
			con.createSubcontext(JNDI_SUBCONTEXT1);
			con.createSubcontext(JNDI_SUBCONTEXT2);
			con.rebind(JNDI_DATASOURCE_URL, pool);
//			con.lookup("java:/MyDBPostgresDS");
			System.out.println("Start");*/
			
//			DataSource ds = (DataSource) con.lookup("java:/PostgresDS");
			/*System.out.println(con.getEnvironment());
			
			Enumeration<NameClassPair> values = con.list("");
			while(values.hasMoreElements())
			{
				NameClassPair pair = values.nextElement();
				System.out.println(pair.getName()+"\t"+pair.getNameInNamespace());
			}*/
//			System.out.println(ds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
	     final class DatabaseContext extends InitialContext {

	         DatabaseContext() throws NamingException {}

	         @Override
	         public Object lookup(String name) throws NamingException
	         {
	             try {
	                 //our connection strings
	                 Class.forName("org.postgresql.Driver");
//	                 DataSource ds1 = new LocalDataSource("jdbc:postgresql://localhost:5432/employeedb", "postgres", "postgres");
	                 
	             	PGConnectionPoolDataSource pool = new PGConnectionPoolDataSource();
	    			pool.setUrl("jdbc:postgresql://localhost:5432/employeedb");
	    			pool.setUser("postgres");
	    			pool.setPassword("postgres");
//	                 DataSource ds2 = new LocalDataSource("jdbc:mysql://dbserver1/dboneB", "username", "xxxpass");

	                 Properties prop = new Properties();
	                 prop.put("PostgresDS", pool);
//	                 prop.put("jdbc/ds2", ds2);
	                 
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
	     
		InitContDSConnection ds = new InitContDSConnection();
		ds.getConnection();
	}
	
	public void getData(){
		/*String selectQuery = "select * from employee.employee_details where \"empId\"=?";
		   try {
			PreparedStatement ps =  c.prepareStatement(selectQuery);
			ps.setInt(1, 3);
			   ResultSet rs = ps.executeQuery();
			   while(rs.next()){
			  	 System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4));
			   }
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
	}
}
