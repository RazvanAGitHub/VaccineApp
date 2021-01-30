package com.springboot.covid19.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.covid19.entity.Vaccine;
import com.springboot.covid19.service.VaccineService;

@RestController
@RequestMapping("/api/vaccines")
public class VaccineRestController {
	@Autowired
	private VaccineService vaccineService;
	
	@GetMapping("")
	public List<VaccineRest> getVaccines() {
		
		List<Vaccine> list = vaccineService.findAll();
		List<VaccineRest> vaccines = new ArrayList<>();
		list.forEach(vaccine -> {
			VaccineRest vaccineRest = new VaccineRest(
					vaccine.getId(),
					vaccine.getManufacturer(),
					vaccine.getMinAge(),
					vaccine.getStorageTemperature(),
					vaccine.getInStock()
				);
			vaccines.add(vaccineRest);
		});
		
		return vaccines;
	}
	
	@GetMapping("/{id}")
	public VaccineRest getVaccine(@PathVariable int id) {
		Vaccine vaccine = vaccineService.findById(id);
		VaccineRest vaccineRest = new VaccineRest(
				vaccine.getId(),
				vaccine.getManufacturer(),
				vaccine.getMinAge(),
				vaccine.getStorageTemperature(),
				vaccine.getInStock()
			);
		
		return vaccineRest;
	}
}
