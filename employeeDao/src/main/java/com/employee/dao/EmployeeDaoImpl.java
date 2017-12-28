package com.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		List<EmployeeDaoResponse> listDto = new ArrayList<EmployeeDaoResponse>();
		String selectQuery = "select * from employee.employee_details";
		if(empId>0) {
			selectQuery = selectQuery+" where \"empId\"=?";
		}
		try {
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(selectQuery);
			if(empId>0) {
				ps.setInt(1, empId);
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				EmployeeDaoResponse dto = new EmployeeDaoResponse();
				dto.setEmpId(rs.getInt(1));
				dto.setEmpName(rs.getString(2));
				dto.setEmpAddress(rs.getString(3));
				dto.setGender(rs.getString(rs.getString(4)));
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

}
