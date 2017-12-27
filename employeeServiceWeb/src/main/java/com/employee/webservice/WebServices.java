package com.employee.webservice;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.employee.service.BaseServices;
import com.employee.service.BaseServicesImpl;


@Path("/api")
public class WebServices {

    @GET
    @Path("/getName")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getChargebacksSparkBar(@QueryParam("name") String name, @Context HttpServletRequest httpRequest) {
    	System.out.println("Hi we are in web service layer");
    	BaseServices service = new BaseServicesImpl();
    	service.getServiceLayerDetails();
    	Test test = new Test();
    	test.setId(1);
    	test.setName(name);
        return Response.ok(test).build();
    }

}