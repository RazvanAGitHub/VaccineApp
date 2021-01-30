package com.springboot.covid19.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.covid19.entity.User;
import com.springboot.covid19.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
	@Autowired
	private UserService userService;
	
	@GetMapping("")
	public List<UserRest> getUsers() {
		List<User> list = userService.findUsersByPriorityAndRegistration();
		List<UserRest> users = new ArrayList<>();
		list.forEach(user-> {
			UserRest userRest = new UserRest(
					user.getId(),
					user.getName(),
					user.getCnp(),
					user.getAddress(),
					user.getEmail(),
					user.getPhone(),
					user.getPriority(),
					user.getRegistration(),
					user.getVaccine().getManufacturer(),
					user.getAppointment()
				);
			users.add(userRest);
		});
		
		
		return users;
	}
	
	@GetMapping("/{id}")
	public UserRest getUser(@PathVariable int id) {
		User user = userService.findById(id);
		UserRest userRest = new UserRest(
				user.getId(),
				user.getName(),
				user.getCnp(),
				user.getAddress(),
				user.getEmail(),
				user.getPhone(),
				user.getPriority(),
				user.getRegistration(),
				user.getVaccine().getManufacturer(),
				user.getAppointment()
			);
		
		return userRest;
	}
}
