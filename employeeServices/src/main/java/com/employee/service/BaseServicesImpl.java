package com.employee.service;

import com.employee.dao.BaseDao;
import com.employee.dao.BaseDaoImpl;


public class BaseServicesImpl implements BaseServices {
	 public void getServiceLayerDetails(){
		   System.out.println("Hello We are in service layer");
		   BaseDao dao = new BaseDaoImpl();
		   dao.getDaoLayerDetails();
	   }
}
