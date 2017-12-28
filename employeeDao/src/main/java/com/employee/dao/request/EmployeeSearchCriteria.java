package com.employee.dao.request;

import java.io.Serializable;

public class EmployeeSearchCriteria implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int empId;
	private String empName;
	private String empAddress;
	private String gender;

	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpAddress() {
		return empAddress;
	}
	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
}

