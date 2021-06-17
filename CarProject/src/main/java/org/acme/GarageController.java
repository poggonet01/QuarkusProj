package org.acme;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


@Path("v1/garages")
public class GarageController {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGarages() {
		List<Garage> garagesList = Garage.getAllGarages();
		return Response.ok(garagesList).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/certainGarage")
	public Response getGarageById(@QueryParam("id") long id) {
		Garage newGarage =  Garage.findGarageById(id);
		if (newGarage != null) {
			if (!newGarage.cars.isEmpty()) {
				return Response.ok(newGarage.cars).build();
			}
			return Response.ok(newGarage).build();
		}
		return Response.status(Response.Status.NOT_FOUND).entity("Garage with id=" + id + " doesn't exist").build();
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
	
	@PATCH
	@Transactional
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addCar")
	public Response addCar(@QueryParam("carId") long carId , @QueryParam("garageId") long garageId )  {
		Garage newGarage = Garage.findGarageById(garageId);
		Car car = Car.findCarById(carId);
		if (newGarage == null) {
			Response.status(Response.Status.NOT_FOUND).entity("Garage with id=" + garageId + " doesn't exist").build();
		}
		if (newGarage.dimension <= newGarage.cars.size()) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Garage is full").build();
		}
		if (car != null) {
			newGarage.cars.add(car);
			car.garage = newGarage;
			car.persist();
			newGarage.persist();
			return Response.ok(newGarage.cars).build();
	    }
		return Response.status(Response.Status.NOT_FOUND).entity("Car with id=" + carId + " doesn't exist").build();
	}
	
	@DELETE
	@Transactional
	@Produces(MediaType.APPLICATION_JSON)
	@Path("deleteGarage")
	public Response deleteGarage(@QueryParam("garageId") long garageId) {
		boolean isDeleted = Garage.deleteById(garageId);
		if (isDeleted) {
			return Response.ok(isDeleted).build();
		}
		return Response.status(Response.Status.NOT_FOUND).entity("Garage with id=" + garageId + " doesn't exist").build();
	}
	
	@PATCH
	@Transactional
	@Produces(MediaType.APPLICATION_JSON)
	@Path("deleteCarFromGarage") 
	public Response deleteCarFromGarage(@QueryParam("carId")  long carId , @QueryParam("garageId") long garageId ) {
		Garage newGarage = Garage.findGarageById(garageId);
		Car car = Car.findCarById(carId);
		if (newGarage == null) {
			Response.status(Response.Status.NOT_FOUND).entity("Garage with id=" + garageId + " doesn't exist").build();
		}
		if (newGarage.dimension <= newGarage.cars.size()) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Garage is full").build();
		}
		if (car != null) {
			newGarage.cars.remove(car);
			car.garage = null;
			car.persist();
			newGarage.persist();
			if (!newGarage.cars.isEmpty())
			{
				return Response.ok(newGarage.cars).build();
			}
			return Response.ok(newGarage).build();
	    }
		return Response.status(Response.Status.NOT_FOUND).entity("Car with id=" + carId + " doesn't exist").build();
				
	}
}
	
