package com.employee.webservice;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.apache.commons.beanutils.BeanUtils;
import org.glassfish.jersey.client.ClientConfig;

import com.employee.dao.EmployeeDaoImpl;
import com.employee.dao.response.EmployeeDaoResponse;
import com.employee.service.response.EmployeeServiceResponse;

public class WebServicesTest {
	/*public static void main(String[] args) {
		ClientConfig config = new ClientConfig();

		Client client = ClientBuilder.newClient(config);

		WebTarget target = client.target(getBaseURI());

		String response = target.path("api").path("getName")
				.queryParam("name", "Ankush").request()
				.accept(MediaType.APPLICATION_JSON).get(String.class)
				.toString();
		System.out.println(response);
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:18080/employee")
				.build();
	}*/
	
	public static void main(String[] args) {
			
			List<EmployeeServiceResponse> serviceList = new ArrayList<EmployeeServiceResponse>();
			
			List<EmployeeDaoResponse> daoList = new ArrayList<EmployeeDaoResponse>();
			EmployeeDaoResponse daoResponse = new EmployeeDaoResponse();
			daoResponse.setEmpId(1);
			daoResponse.setEmpName("Ram");
			daoList.add(daoResponse);
			
			try {
				for(EmployeeDaoResponse dao : daoList){
					EmployeeServiceResponse serviceResponse = new EmployeeServiceResponse();
					BeanUtils.copyProperties(serviceResponse, dao );
					serviceList.add(serviceResponse);
				}
				
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
			
			for(EmployeeServiceResponse res : serviceList){
				System.out.println(res.getEmpId());
				System.out.println(res.getEmpName());
			}
	}
}
