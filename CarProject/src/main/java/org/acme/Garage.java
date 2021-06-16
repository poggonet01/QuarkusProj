package org.acme;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name="Garage")
public class Garage extends PanacheEntityBase {
	
	@Id
	@Column(name = "GarageId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long garageId;
	
//	@OneToMany(mappedBy = "garage" , orphanRemoval = true)
//	@Column(name="CarId")
//	public List<Car> cars;
	
	@Column(name="GarageDimension" , length = 10)
	public Long dimension;
}
