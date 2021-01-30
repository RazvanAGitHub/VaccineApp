package com.springboot.covid19.rest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserRest {
	private int id;
	private String name;
	private String cnp;
	private String address;
	private String email;
	private String phone;
	private int priority;
	private LocalDateTime registration;
	private String vaccineManufacturer;
	private LocalDate appointment;
	
	public UserRest(int id, String name, String cnp, String address, String email, String phone, int priority,
			LocalDateTime registration, String vaccineManufacturer, LocalDate appointment) {
		this.id = id;
		this.name = name;
		this.cnp = cnp;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.priority = priority;
		this.registration = registration;
		this.vaccineManufacturer = vaccineManufacturer;
		this.appointment = appointment;
	}
	
}
