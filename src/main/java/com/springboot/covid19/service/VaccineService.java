package com.springboot.covid19.service;

import java.util.List;

import com.springboot.covid19.entity.Vaccine;

public interface VaccineService {

	public List<Vaccine> findAll();
	
	public Vaccine findById(int theId);
	
	public void save(Vaccine theVaccine);
	
	public void deleteById(int theId);
	
	public boolean existsById(int id);
}
