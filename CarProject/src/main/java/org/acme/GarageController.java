package org.acme;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("v1/garages")
public class GarageController {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response getGarages() {
		List<Garage> carList = Garage.getAllGarages();
		return Response.ok(carList).build();
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/certainGarage")
	public Response getGarageById(@QueryParam("id") long id) {
		Garage newGarage =  Garage.findGarageById(id);
		return Response.ok(newGarage).build();
	}
	
	@POST
	@Transactional
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addGarage")
	public Response addGarage(Garage garage) {
		Garage newGarage = new Garage();
		newGarage.cars = garage.cars;
		newGarage.dimension = garage.dimension;
		garage.persist();
		return Response.ok(newGarage).build();
	}
	
	@PUT
	@Transactional
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/addCar")
	public Response addCar(@QueryParam("carId") long carId , @QueryParam("garageId") long garageId ) {
		Garage newGarage = Garage.findGarageById(garageId);
		if (newGarage.dimension <= newGarage.cars.size()) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Garage is full").build();
		}
		Car car = Car.findCarById(carId);
		if (car != null) {
			newGarage.cars.add(car);
			car.garage = newGarage;
			car.persist();
			newGarage.persist();
		}
		return Response.ok(newGarage).build();
	}
	
	@DELETE
	@Transactional
	@Produces(MediaType.APPLICATION_JSON)
	@Path("deleteGarage")
	public Response deleteGarage(@QueryParam("garageId") long garageId) {
		boolean isDeleted = Garage.deleteById(garageId);
		return Response.ok(isDeleted).build();
	}
}
