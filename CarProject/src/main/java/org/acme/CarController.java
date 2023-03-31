package org.acme;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.Query;

@Path("/v1/cars")
public class CarController {
	
	private final Car car;
	
	@Inject
	public CarController(Car car) {
		this.car = car;
	}
	 
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCars() {
		List<Car> carList = Car.getAllCars();
		return Response.ok(carList).build();
	}
	
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/certainCar")
	public Response getCarById(@QueryParam("id") long id) {
	    Car newCar =  Car.findCarById(id);
		Car newCar1 = newCar;
		List<Car> carss = new ArrayList<>();
		System.out.println("dawdaw");
	    if (newCar != null) {
	    	return Response.ok(newCar).build();
	    }
	    return Response.status(Response.Status.NOT_FOUND).entity("Car with id=" + id + " doesn't exist").build();
	}
	@POST
	@Transactional
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/add")
	public Response addCar(Car newCar) {
		car.persist(newCar);
		System.out.println("dawda");
		return Response.ok(car).build();
	}
	
	@PATCH
	@Transactional
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/modify")
	public Response changeColor(@QueryParam("id") long id , @QueryParam("color") String color) {
		Car newCar = Car.findCarById(id);
		if (newCar != null) {
			newCar.color = color;
			//newCar.persist();
			return Response.ok(newCar).build();
		}
		int k = 2;
		return Response.status(Response.Status.NOT_FOUND).entity("" + id + " doesn't exist").build();
	}
	
	@DELETE
	@Transactional
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/delete")
	public Response deleteCar(@QueryParam("id") Long id ) {
		boolean isDeleted = Car.deleteCarById(id);
		if (isDeleted) {
			return Response.ok(isDeleted).build();
		}
		return Response.noContent().build();
	}
	
}