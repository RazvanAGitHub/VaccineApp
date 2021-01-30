package com.springboot.covid19.controller;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.covid19.entity.User;
import com.springboot.covid19.entity.Vaccine;
import com.springboot.covid19.service.UserService;
import com.springboot.covid19.service.VaccineService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private VaccineService vaccineService;
	
	@Value("${hospital.day.vaccination.capacity}")
	private int hospitalVaccinationCapacity;

	@Value("${appointment.start.fromtoday.days}")
	private int appointmentStart;
	
	private LocalDateTime userRegistration;
	
	// add mapping for "/list"

	@GetMapping("/list")
	public String listUsers(Model theModel) {
		
		// get Users from db
		List<User> theUsers = userService.findUsersByPriorityAndRegistration();
		
		// add to the spring model
		theModel.addAttribute("users", theUsers);
		
		return "users/list-users";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		User theUser = new User();
		List<Vaccine> vaccines = vaccineService.findAll();
		
		theModel.addAttribute("user", theUser);
		theModel.addAttribute("vaccines", vaccines);
		
		return "users/user-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("userId") int theId,
									Model theModel) {
		
		// get the User from the service
		User user = userService.findById(theId);
		userRegistration = user.getRegistration();
		
		List<Vaccine> vaccines = vaccineService.findAll();

		// set User as a model attribute to pre-populate the form
		theModel.addAttribute("user", user);
		theModel.addAttribute("vaccines", vaccines);
		
		// send over to our form
		return "users/user-form";			
	}
		
	@PostMapping("/save")
	public String saveUser(@ModelAttribute("user") User user) {
		String redirect = "redirect:/users/appointment-added";
		
		// save the user
		if(user.getId() == 0) user.setRegistration(LocalDateTime.now());
		else {
			user.setRegistration(userRegistration);
			redirect = "redirect:/users/list";
		}
		
		userService.save(user);
		
		// use a redirect to prevent duplicate submissions
		return redirect;
	}
	
	@GetMapping("/appointment-added")
	public String showAppointmentAdded() {
		return "users/appointment-added";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("userId") int theId) {
		
		// delete the User
		userService.deleteById(theId);
		
		// redirect to /Users/list
		return "redirect:/users/list";
	}
	
	@GetMapping("/setAppointmentDate")
	public String setAppointmentDate() {
		List<User> users = userService.findUsersByPriorityAndRegistration();
		int index = 0;
		int capacity = hospitalVaccinationCapacity > 0 ? hospitalVaccinationCapacity : 1;
		
		for(User user: users) {
			int plusDays = appointmentStart;
			
			
			plusDays += index++ / capacity;  
					
			userService.setAppointment(user.getId(), LocalDate.now().plusDays(plusDays));
		}
		
		return "redirect:/users/list";
	}
	
	@GetMapping("/clearAppointmentDate")
	public String clearAppointmentDate() {
		List<User> users = userService.findUsersByPriorityAndRegistration();
		
		for(User user: users) {
			userService.setAppointment(user.getId(), null);
		}
		
		return "redirect:/users/list";
	}
}


















