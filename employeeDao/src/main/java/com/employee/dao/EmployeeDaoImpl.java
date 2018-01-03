package com.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.jboss.as.remoting.Namespace;

import com.employee.dao.request.EmployeeSearchCriteria;
import com.employee.dao.response.EmployeeDaoResponse;

public class EmployeeDaoImpl implements EmployeeDao {
	private PreparedStatement ps;
	private Connection conn;

	@Override
	public boolean insertQuery(EmployeeSearchCriteria searchCriteria) {
		boolean inserted = false;
		conn = DBConnection.getConnection();
		String insertQuery = "INSERT INTO EMPLOYEE.EMPLOYEE_DETAILS (\"empId\",\"empName\",\"empAddress\",\"gender\") VALUES (?, ?, ?, ? )";
		try {
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(insertQuery);
			ps.setInt(1, searchCriteria.getEmpId());
			ps.setString(2, searchCriteria.getEmpName());
			ps.setString(3, searchCriteria.getEmpAddress());
			ps.setString(4, searchCriteria.getGender());
			
			int result = ps.executeUpdate();
			if(result>0){
				inserted = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closePreparedStatement(ps);
			DBConnection.closeConnection(conn);
		}
		return inserted;
	}

	@Override
	public List<EmployeeDaoResponse> selectQuery(int empId) {
		System.out.println("Calling getConnection");
		conn = getConnection();
		System.out.println("Done");
		List<EmployeeDaoResponse> listDto = new ArrayList<EmployeeDaoResponse>();
		String selectQuery = "select * from employee.employee_details";
		if(empId>0) {
			selectQuery = selectQuery+" where \"empId\"=?";
		}
		try {
			if(conn==null){
				System.out.println("Value of ds.getConnection is null");
				conn = DBConnection.getConnection();
			}
			System.out.println("Value of ds.getConnection is not null");
			System.out.println("Value of selectQuerty "+selectQuery);
			PreparedStatement ps = conn.prepareStatement(selectQuery);
			if(empId>0) {
				ps.setInt(1, empId);
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("We are in resultSet.next()");
				EmployeeDaoResponse dto = new EmployeeDaoResponse();
				dto.setEmpId(rs.getInt(1));
				dto.setEmpName(rs.getString(2));
				dto.setEmpAddress(rs.getString(3));
				dto.setGender(rs.getString(4));
				listDto.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closePreparedStatement(ps);
			DBConnection.closeConnection(conn);
		}

		return listDto;
	}

	@Override
	public boolean updateQuery(int empId) {
		boolean updated = false;
		String updateQuery = "update EMPLOYEE.EMPLOYEE_DETAILS set \"empAddress\" = 'London' where \"empId\"=?";
		try {
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(updateQuery);
			ps.setInt(1, empId);
			int result = ps.executeUpdate();
			if(result>0) {
				updated = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closePreparedStatement(ps);
			DBConnection.closeConnection(conn);
		}
		return updated;
	}

	@Override
	public boolean delteQuery(int empId) {
		boolean deleted = false;
		String deleteQuery = "delete from employee.employee_details where \"empId\"=?";
		try {
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(deleteQuery);
			ps.setInt(1, empId);
			int result = ps.executeUpdate();
			if(result>0){
				deleted=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closePreparedStatement(ps);
			DBConnection.closeConnection(conn);
		}

		return deleted;
	}
	
	public Connection getConnection(){
		Connection conn = null;
		try {
			/*Properties props = new Properties();
			props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.InitialContextFactory");
			props.setProperty(Context.PROVIDER_URL, "localhost:8080");
			props.setProperty(Context.SECURITY_PRINCIPAL, "sa");
			props.setProperty(Context.SECURITY_AUTHENTICATION, "sa");
			*/
			Context con = new InitialContext();
			DataSource ds = (DataSource)con.lookup("java:/PostgresDS");
			try {
				Enumeration<NameClassPair> names = con.list("");
				System.out.println("Before while loop");
				while(names.hasMoreElements()){
					System.out.println("inside while loop");
					NameClassPair pair = names.nextElement();
					System.out.println(pair.getName()+"\t"+pair.getNameInNamespace());
				}
				System.out.println("End of while loop");
			}catch(Exception  e){
				System.out.println("Exception in nameclasspair");
				e.printStackTrace();
			}
			
			
			conn = ds.getConnection();
			System.out.println("Value of Connection : "+conn);
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
/*	public void main(String[] args) {
		DataSourceConnection ds = new DataSourceConnection();
		ds.getConnection();
	}*/

}
