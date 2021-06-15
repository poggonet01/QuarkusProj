package org.acme;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.Query;

@Path("/v1/cars")
public class ExampleResource {
	public static List<String> cars = new ArrayList<String>();
//	private final Car car;
//	
//    @Inject
//    public ExampleResource(Car car) { this.car = car; }
	 
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response getCars() {
		Car car = new Car();
		List<Car> carList = car.getAllCars();
		return Response.ok(carList).build();
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/single")
	public Response getCarByName(@QueryParam("name") String name) {
		Car car = new Car();
	    Car newCar =  car.findByName(name);
		return Response.ok(newCar).build();
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/single")
	public Response getCarById(@QueryParam("id") long id) {
		Car car = new Car();
	    Car newCar =  car.findCarById(id);
		return Response.ok(newCar).build();
	}
	
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/add")
	public Response addCar(@QueryParam("name") String name) {
		Car newCar = new Car();
		newCar.id = (long) 12;
		newCar.color = "dawawdfaff";
		newCar.saveCar(newCar);
		return Response.ok(newCar).build();
	}
	
}