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
	public Long Id;
	
	@Column(name = "CarName" , nullable= false) 
	public String Name;
	
	@Column(name = "CarColor" , length = 10 , nullable = false)
	public String Color;
	
	public Car () {}
	public Car(Long Id ,String name, String color) {
		this.Id = Id;
		this.Name = name;
		this.Color = color;
	}
		

}
