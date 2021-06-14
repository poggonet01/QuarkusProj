package org.acme;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	public Car findCarById(Long id) {
		return findById(id);
	}
	
	public Car findByName(String name) {
		return find("name" , name).firstResult();
	}
	public void deleteCarByName(String name) {
		delete("name", name);
	}
		

}
