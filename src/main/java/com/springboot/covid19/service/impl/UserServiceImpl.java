package com.springboot.covid19.service.impl;

import java.time.LocalDate;
import java.util.*;

import com.springboot.covid19.dto.UserViewDto;
import com.springboot.covid19.dto.UserViewDtoShort;
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

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int id) {
        Optional<User> result = userRepository.findById(id);

        User theUser = null;

        if (result.isPresent()) {
            theUser = result.get();
        } else {
            throw new NotFoundException("User with id: " + id + " not found.");
        }

        return theUser;
    }

    @Override
    public List<UserViewDto> findAllByPriority(int priority) {

        List<UserViewDto> usersList = new ArrayList<>();

        // user -> userViewDto (modelMapper -> TODO look it up in the MVN repository, it can be a BEAN)
        // it maps all of the fields from one blueprint to another matching by field name.
        userRepository.findAll()
                // User -> UserViewDto -> add to a LIST
                .forEach(user -> {
                    if (user.getPriority() == priority) {
                        usersList.add(new UserViewDto(user.getName(), user.getProfession(), user.getCnp(), user.getPriority()));
                    }

                });

        return usersList;
    }

    @Override
    public void save(User theUser) {
        //System.out.println("************** " + theUser.getName() + " " + theUser.getAppointment() + ", priority is " + theUser.getPriority() + " _ " + theUser.getCnp() +" _ " + theUser.getProfession());
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
    public List<UserViewDto> findUsersWithAgeOver65() {
        List<UserViewDto> usersList = new ArrayList<>();

        userRepository.findAll()

                .forEach(user -> {
                    if (ageOfTheUser(user) >= 65) {
                        usersList.add(new UserViewDto(user.getName(), user.getCnp(), user.getProfession(), user.getPriority()));
                    }

                });
        return usersList;

    }

    @Override
    public List<UserViewDtoShort> findUsersThatWillBeVaccinated() {
        List<UserViewDtoShort> usersList = new ArrayList<>();
        LocalDate today = LocalDate.now();

        userRepository.findAll()
                .forEach(user -> {
                    if (user.getAppointment().isAfter(today)) {
                        usersList.add(new UserViewDtoShort(user.getName(), user.getAppointment()));
                    }

                });
        //Collections.sort(usersList, Comparator.comparing(UserViewDtoShort::getName()));

        Comparator<UserViewDtoShort> comparator
                = (h1, h2) -> h1.getAppointment().compareTo(h2.getAppointment());
        usersList.sort(comparator);
        return usersList;

    }

    @Override
    public void setAppointment(int id, LocalDate dt) {
        userRepository.setAppointment(id, dt);
    }

    @Override
    public int countCapacityForADate(LocalDate appointment, int priority) {
        return userRepository.countCapacityForADate(appointment, priority);
    }

    @Override
    public LocalDate getLastDateForAppointment(int priority) {
        return userRepository.getLastDateForAppointment(priority).get();
    }

    @Override
    public boolean existsById(int id) {
        return userRepository.existsById(id);
    }

    private int ageOfTheUser(User theUser) {
        int age;
        int determineTheCentury = Integer.parseInt(theUser.getCnp().substring(0, 1));
        int yearOfBirth = 0;

        if (determineTheCentury <= 2) {
            yearOfBirth = 1900 + Integer.parseInt(theUser.getCnp().substring(1, 3));
        } else if (determineTheCentury >= 5) {
            yearOfBirth = 2000 + Integer.parseInt(theUser.getCnp().substring(1, 3));
        }
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();

        age = year - yearOfBirth;
        return age;
    }

    @Override
    public void setAppointmentDateForAUser(User user, int capacity) {
        LocalDate startingDatePriority1 = LocalDate.of(2021, 02, 15);
        LocalDate startingDatePriority2 = LocalDate.of(2021, 03, 15);
        LocalDate startingDatePriority3 = LocalDate.of(2021, 04, 15);
        LocalDate startingDatePriority = startingDatePriority1;

        int priority = user.getPriority();
        switch (priority) {
            case 2:
                startingDatePriority = startingDatePriority2;
                break;
            case 3:
                startingDatePriority = startingDatePriority3;
                break;
        }

        if (countCapacityForADate(startingDatePriority, priority) == 0) {
            setAppointment(user.getId(), startingDatePriority);
        } else if (countCapacityForADate(getLastDateForAppointment(priority), priority) < capacity) {
            setAppointment(user.getId(), getLastDateForAppointment(priority));
        } else {
            setAppointment(user.getId(), getLastDateForAppointment(priority).plusDays(1));
        }
    }
}






