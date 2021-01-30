package com.springboot.covid19.service;

import java.time.LocalDate;
import java.util.List;

import com.springboot.covid19.entity.User;

public interface UserService {

	public List<User> findAll();
	
	public User findById(int theId);
	
	public void save(User theUser);
	
	public void deleteById(int theId);
	
	public List<User> findUsersByPriorityAndRegistration();
	
	public void setAppointment(int id, LocalDate dt);
	
	public boolean existsById(int id);
}
