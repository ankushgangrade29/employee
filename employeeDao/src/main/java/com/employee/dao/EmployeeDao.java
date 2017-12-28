package com.employee.dao;

import java.util.List;

import com.employee.dao.request.EmployeeSearchCriteria;
import com.employee.dao.response.EmployeeDaoResponse;

public interface EmployeeDao {
	public boolean insertQuery(EmployeeSearchCriteria searchCriteria);
	public List<EmployeeDaoResponse> selectQuery(int empId);
	public boolean updateQuery(int empId);
	public boolean delteQuery(int empId);
}
