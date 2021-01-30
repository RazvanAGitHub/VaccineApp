package com.springboot.covid19.dto;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.covid19.entity.Vaccine;

public interface VaccineRepository extends JpaRepository<Vaccine, Integer> {

}
