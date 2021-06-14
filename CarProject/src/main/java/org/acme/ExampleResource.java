package org.acme;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/cars")
public class ExampleResource {
	public static List<String> cars = new ArrayList<String>();
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response getCars() {
		return Response.ok(cars).build();
	}
	
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/size")
	public Integer countCars() {
		return (int) Car.count();
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public Response addCar(String newCar) {
		cars.add(newCar);
		return Response.ok(cars).build();
	}
	
}