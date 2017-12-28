package com.employee.service;

import java.util.List;

import com.employee.service.request.EmployeeServiceRequest;
import com.employee.service.response.EmployeeServiceResponse;

public interface EmployeeService {
	public boolean createEmployeeRecords(EmployeeServiceRequest emp);
	public List<EmployeeServiceResponse> getEmployeeRecords(int empId);
	public boolean updateEmployeeRecords(int empId);
	public boolean deleteEmployeeRecords(int empId);
}
