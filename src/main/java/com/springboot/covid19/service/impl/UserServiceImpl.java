package com.springboot.covid19.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.springboot.covid19.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.covid19.repository.UserRepository;
import com.springboot.covid19.entity.User;
import com.springboot.covid19.exception.NotFoundException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

//	public UserServiceImpl(UserRepository userRepository) {
//		this.userRepository = userRepository;
//	}
		
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(int id) {
		Optional<User> result = userRepository.findById(id);
		
		User theUser = null;
		
		if (result.isPresent()) {
			theUser= result.get();
		}
		else {
			throw new NotFoundException("User with id: " + id  + " not found.");
		}
		
		return theUser;
	}

	@Override
	public void save(User theUser) {
		userRepository.save(theUser);
	}

	@Override
	public void deleteById(int theId) {
		userRepository.deleteById(theId);
	}

	@Override
	public List<User> findUsersByPriorityAndRegistration() {
		List<User> users = userRepository.findAll(Sort.by("priority", "registration"));
		return users;
	}

	@Override
	public void setAppointment(int id, LocalDate dt) {
		userRepository.setAppointment(id, dt);
	}

	@Override
	public boolean existsById(int id) {
		return userRepository.existsById(id);
	}
}






