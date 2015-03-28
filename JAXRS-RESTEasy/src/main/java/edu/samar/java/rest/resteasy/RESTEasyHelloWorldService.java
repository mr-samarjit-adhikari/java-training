package edu.samar.java.rest.resteasy;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
 
@Path("/helloworld")
public class RESTEasyHelloWorldService {
	
    public static final String HELLOWORLD_MESSAGE = "Hello World!";
	 
	@GET
	@Produces("text/plain")
	public String getHello() {
	    return HELLOWORLD_MESSAGE;
	}

}
