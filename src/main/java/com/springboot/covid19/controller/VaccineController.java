package com.springboot.covid19.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.covid19.entity.Vaccine;
import com.springboot.covid19.service.VaccineService;

@Controller
@RequestMapping("/vaccines")
public class VaccineController {

	@Autowired
	private VaccineService vaccineService;
	
	// add mapping for "/list"

	@GetMapping("/list")
	public String listVaccines(Model theModel) {
		
		// get Vaccines from db
		List<Vaccine> theVaccines = vaccineService.findAll();
		
		// add to the spring model
		theModel.addAttribute("vaccines", theVaccines);
		
		return "vaccines/list-vaccines";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Vaccine theVaccine = new Vaccine();
		
		theModel.addAttribute("vaccine", theVaccine);
		
		return "vaccines/vaccine-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("vaccineId") int theId,
									Model theModel) {
		
		// get the Vaccine from the service
		Vaccine theVaccine= vaccineService.findById(theId);
		
		// set Vaccine as a model attribute to pre-populate the form
		theModel.addAttribute("vaccine", theVaccine);
		
		// send over to our form
		return "vaccines/vaccine-form";			
	}
	
	
	@PostMapping("/save")
	public String saveVaccine(@ModelAttribute("vaccine") Vaccine theVaccine) {
		
		// save the Vaccine
		vaccineService.save(theVaccine);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/vaccines/list";
	}
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam("vaccineId") int theId) {
		
		// delete the Vaccine
		vaccineService.deleteById(theId);
		
		// redirect to /Vaccines/list
		return "redirect:/vaccines/list";
	}
}


















