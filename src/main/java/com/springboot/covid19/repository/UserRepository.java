package com.springboot.covid19.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.covid19.entity.User;
import org.springframework.data.domain.Sort;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public List<User> findAll(Sort sort);

    @Modifying
    @Query("UPDATE User user SET user.appointment = ?2 WHERE (id = ?1)")
    @Transactional
    public void setAppointment(int id, LocalDate dt);

    @Query("select count(user.appointment) from User user where user.appointment = ?1 and user.priority = ?2")
    int countCapacityForADate(LocalDate appointment, int priority);

    @Query("select max(u.appointment) from User u where u.priority = ?1 group by u.priority")
    Optional<LocalDate> getLastDateForAppointment(int priority);
}
