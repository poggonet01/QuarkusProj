package org.acme;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.resource.spi.ConfigProperty;
import javax.transaction.Transactional;
import javax.ws.rs.ApplicationPath;

import org.hibernate.event.spi.SaveOrUpdateEvent;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "CarTypes")
public class Car extends PanacheEntityBase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	@Column(name = "CarName" , nullable= false) 
	public String name;
	
	@Column(name = "CarColor" , length = 10 , nullable = false)
	public String color;
	
	public Car () {}
	public Car(Long id ,String name, String color) {
		this.id = id;
		this.name = name;
		this.color = color;
	}
	
	@Override
	public String toString() {
		return "Car [id=" + id + ", name=" + name + ", color=" + color + "]";
	}
	public static Car findCarById(Long id) {
		return findById(id);
	}
	
	public static List<Car> getAllCars() {
		return listAll();
	}
	
	@Transactional
	public static void deleteAllCars() {
		deleteAll();
	}
	
	@Transactional
	public static boolean deleteCarById(Long id) {
		return deleteById(id);
	}
		

}
