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
		if (ageOfTheUser(theUser) >= 65 && !theUser.getProfession().equals("Doctor")) {
			theUser.setPriority(2);
		} else {
			switch (theUser.getProfession()) {
				case "Doctor":
					theUser.setPriority(1);
					break;
				case "Teacher":
					theUser.setPriority(2);
					break;
				case "Other":
					theUser.setPriority(3);
					break;
			}
		}

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

	private int ageOfTheUser(User theUser) {
		int age;
		int determineTheCentury = Integer.parseInt(theUser.getCnp().substring(0,1));
		int yearOfBirth = 0;

		if (determineTheCentury <= 2) {
			yearOfBirth = 1900 + Integer.parseInt(theUser.getCnp().substring(1,3));
		} else if (determineTheCentury >= 5) {
			yearOfBirth = 2000 + Integer.parseInt(theUser.getCnp().substring(1,3));
		}
		LocalDate currentDate = LocalDate.now();
		int year = currentDate.getYear();

		age = year - yearOfBirth;
		return age;
	}
}






