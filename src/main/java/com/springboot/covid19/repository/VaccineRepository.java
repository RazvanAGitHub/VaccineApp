package com.springboot.covid19.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.covid19.entity.Vaccine;
import org.springframework.stereotype.Repository;

@Repository//adaugatd de mine
public interface VaccineRepository extends JpaRepository<Vaccine, Integer> {

}
