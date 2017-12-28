package com.employee.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.employee.dao.EmployeeDao;
import com.employee.dao.EmployeeDaoImpl;
import com.employee.dao.request.EmployeeSearchCriteria;
import com.employee.dao.response.EmployeeDaoResponse;
import com.employee.service.request.EmployeeServiceRequest;
import com.employee.service.response.EmployeeServiceResponse;

public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeDao dao;
	
	@Override
	public boolean createEmployeeRecords(EmployeeServiceRequest emp) {
		EmployeeSearchCriteria searchCriteria =  new EmployeeSearchCriteria();
		try {
			BeanUtils.copyProperties(searchCriteria, emp);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		dao = new EmployeeDaoImpl();
		return dao.insertQuery(searchCriteria);
	}

	@Override
	public List<EmployeeServiceResponse> getEmployeeRecords(int empId) {
		List<EmployeeServiceResponse> serviceList = new ArrayList<EmployeeServiceResponse>();
		dao = new EmployeeDaoImpl();
		List<EmployeeDaoResponse> daoList = dao.selectQuery(empId);
		for(EmployeeDaoResponse daoResponse : daoList){
			System.out.println("Value of EMPNAME : " + daoResponse.getEmpName());
		}
		
		try {
			for(EmployeeDaoResponse dao : daoList){
				EmployeeServiceResponse serviceResponse = new EmployeeServiceResponse();
				BeanUtils.copyProperties(serviceResponse, dao );
				serviceList.add(serviceResponse);
			}
			
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		/*
		try {
			BeanUtils.copyProperties(serviceList, daoList);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}*/
		for(EmployeeServiceResponse serviceResponse : serviceList){
			System.out.println("Value of EMPNAME serviceList : " + serviceResponse.getEmpName());
		}
		return serviceList;
	}

	@Override
	public boolean updateEmployeeRecords(int empId) {
		dao = new EmployeeDaoImpl();
		return dao.updateQuery(empId);
	}

	@Override
	public boolean deleteEmployeeRecords(int empId) {
		dao = new EmployeeDaoImpl();
		return dao.delteQuery(empId);
	}

}
