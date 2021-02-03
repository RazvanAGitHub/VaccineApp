package com.springboot.covid19.service;

import java.time.LocalDate;
import java.util.List;

import com.springboot.covid19.dto.UserViewDto;
import com.springboot.covid19.entity.User;

public interface UserService {

	public List<User> findAll();

	List<UserViewDto> findAllByPriority(int priority);
	
	public User findById(int theId);
	
	public void save(User theUser);
	
	public void deleteById(int theId);
	
	public List<User> findUsersByPriorityAndRegistration();
	
	public void setAppointment(int id, LocalDate dt);
	
	public boolean existsById(int id);
}
