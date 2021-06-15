package org.acme;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.transaction.Transactional;

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
	public Car(Long Id ,String name, String color) {
		this.id = Id;
		this.name = name;
		this.color = color;
	}
	
	
	@Override
	public String toString() {
		return "Car [id=" + id + ", name=" + name + ", color=" + color + "]";
	}
	public Car findCarById(Long id) {
		return findById(id);
	}
	
	public Car findByName(String name) {
		return find("name" , name).firstResult();
	}
	
	@Transactional
	public Car saveCar(Car car) {
		this.color = car.color;
		this.id = car.id;
		this.name = car.name;
		persistAndFlush();
		return this;
	}
	
	public List<Car> getAllCars() {
		return listAll();
	}
	
	@Transactional
	public void deleteAllCars() {
		deleteAll();
	}
	
	@Transactional
	public void deleteCarByName(String name) {
		delete("name", name);
	}
		

}
