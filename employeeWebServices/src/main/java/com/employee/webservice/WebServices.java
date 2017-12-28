package com.employee.webservice;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.employee.service.BaseServices;
import com.employee.service.BaseServicesImpl;
import com.employee.service.EmployeeService;
import com.employee.service.EmployeeServiceImpl;
import com.employee.service.request.EmployeeServiceRequest;
import com.employee.service.response.EmployeeServiceResponse;


@Path("/api")
public class WebServices {

    @GET
    @Path("/createEmp")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createEmployeeRecords(@QueryParam("empId") int empId, @QueryParam("empName") String empName, @QueryParam("empAddress") String empAddress,
    		@QueryParam("gender") String gender, @Context HttpServletRequest httpRequest) {
    	System.out.println("Hi we are in web service layer");
    	EmployeeServiceRequest emp = new EmployeeServiceRequest();
    	emp.setEmpId(empId);
    	emp.setEmpName(empName);
    	emp.setEmpAddress(empAddress);
    	emp.setGender(gender);
    	
    	EmployeeService service = new EmployeeServiceImpl();
    	boolean result = service.createEmployeeRecords(emp);
    	if(result){
    		try {
				return Response.created(new URI(httpRequest.getServletContext().getContextPath())).status(Status.CREATED).build();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
    	} else {
    		return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    	}
    		/*
    		
//    	BaseServices service = new BaseServicesImpl();
//    	service.getServiceLayerDetails();
    	Test test = new Test();
    	test.setId(1);
    	test.setName(name);*/
        return  Response.status(Status.OK).build();
    }
    
    @GET
    @Path("/getEmp")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeRecords(@QueryParam("empId") int empId, @Context HttpServletRequest httpRequest) {
    	System.out.println("Hi we are in web service layer");
    	/*BaseServices service = new BaseServicesImpl();
    	service.getServiceLayerDetails();
    	*/
    	EmployeeService service = new EmployeeServiceImpl();
    	List<EmployeeServiceResponse> listResponse = service.getEmployeeRecords(empId);
        return Response.ok(listResponse).build();
    }
    
    @PUT
    @Path("/updateEmp")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmployeeRecords(@QueryParam("empId") int empId, @Context HttpServletRequest httpRequest) {
    	System.out.println("Hi we are in web service layer");
    	/*BaseServices service = new BaseServicesImpl();
    	service.getServiceLayerDetails();*/
    	EmployeeService service = new EmployeeServiceImpl();
    	service.updateEmployeeRecords(empId);
        return Response.accepted().build();
    }
    
    @DELETE
    @Path("/delEmp")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEmployeeRecords(@QueryParam("empId") int empId, @Context HttpServletRequest httpRequest) {
    	System.out.println("Hi we are in web service layer");
    	/*BaseServices service = new BaseServicesImpl();
    	service.getServiceLayerDetails();*/
    	EmployeeService service = new EmployeeServiceImpl();
    	service.deleteEmployeeRecords(empId);
        return Response.accepted().build();
    }

}