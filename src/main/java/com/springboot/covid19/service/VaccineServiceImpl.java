package com.springboot.covid19.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.covid19.dto.VaccineRepository;
import com.springboot.covid19.entity.Vaccine;
import com.springboot.covid19.rest.NotFoundException;

@Service
public class VaccineServiceImpl implements VaccineService {

	@Autowired
	private VaccineRepository vaccineRepository;
		
	@Override
	public List<Vaccine> findAll() {
		return vaccineRepository.findAll();
	}

	@Override
	public Vaccine findById(int id) {
		Optional<Vaccine> result = vaccineRepository.findById(id);
		
		Vaccine theVaccine = null;
		
		if (result.isPresent()) {
			theVaccine= result.get();
		}
		else {
			throw new NotFoundException("Vaccine with id: " + id  + " not found.");
		}
		
		return theVaccine;
	}

	@Override
	public void save(Vaccine theVaccine) {
		vaccineRepository.save(theVaccine);
	}

	@Override
	public void deleteById(int theId) {
		vaccineRepository.deleteById(theId);
	}

	@Override
	public boolean existsById(int id) {
		return vaccineRepository.existsById(id);
	}
}






