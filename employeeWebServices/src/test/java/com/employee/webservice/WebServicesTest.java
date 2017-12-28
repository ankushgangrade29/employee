package com.employee.webservice;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

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
}
