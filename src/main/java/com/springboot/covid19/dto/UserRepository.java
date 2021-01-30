package com.springboot.covid19.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.covid19.entity.User;
import org.springframework.data.domain.Sort;

public interface UserRepository extends JpaRepository<User, Integer> {

	public List<User> findAll(Sort sort);
	
	@Modifying
	@Query("UPDATE User user SET user.appointment = ?2 WHERE (id = ?1)")
	@Transactional
	public void setAppointment(int id, LocalDate dt);
}
