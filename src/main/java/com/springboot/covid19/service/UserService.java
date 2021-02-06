package com.springboot.covid19.service;

import java.time.LocalDate;
import java.util.List;

import com.springboot.covid19.dto.UserViewDto;
import com.springboot.covid19.dto.UserViewDtoShort;
import com.springboot.covid19.entity.User;

public interface UserService {

    public List<User> findAll();

    List<UserViewDto> findAllByPriority(int priority);

    List<UserViewDto> findUsersWithAgeOver65();

    List<UserViewDtoShort> findUsersThatWillBeVaccinated();

    List<UserViewDtoShort> findUsersThatWereVaccinated();

    public User findById(int theId);

    public void save(User theUser);

    public void deleteById(int theId);

    public List<User> findUsersByPriorityAndRegistration();

    public void setAppointment(int id, LocalDate dt);

    void setAppointmentDateForAUser(User user, int capacity);

    int countCapacityForADate(LocalDate appointment, int priority);

    LocalDate getLastDateForAppointment(int priority);

    public boolean existsById(int id);

}
