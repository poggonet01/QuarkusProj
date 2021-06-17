package org.acme;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name="GarageTable")
@ApplicationScoped
public class Garage extends PanacheEntityBase {
	
	@Id
	@Column(name = "GarId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long garageId;
	
	@OneToMany(mappedBy = "garage", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JsonIgnore
	public List<Car> cars = new ArrayList<>();
	
	@Column(name="GarageDimension" , length = 10)
	public Long dimension;
	
	public static List<Garage> getAllGarages() {
		return listAll();
	}
	public static Garage findGarageById(Long id) {
		return findById(id);
	}
	@Override
	public String toString() {
		return "Garage [garageId=" + garageId + ", cars=" + cars + ", dimension=" + dimension + "]";
	}
	
}
