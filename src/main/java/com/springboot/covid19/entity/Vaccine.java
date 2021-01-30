package com.springboot.covid19.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name="vaccine")
@Data
public class Vaccine {
	// define fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="manufacturer")
	private String manufacturer;
	
	@Column(name="min_age")
	private int minAge;
	
	@Column(name="storage_temperature")
	private int storageTemperature;
	
	@Column(name="in_stock")
	private int inStock;
	
	@OneToMany(mappedBy="vaccine")
	private List<User> users;
	
//	method for bi-directional relationship
	public void addUser(User user) {
		if(users == null) {
			users = new ArrayList<User>();
		}
		
		users.add(user);
		user.setVaccine(this);
	}
}











