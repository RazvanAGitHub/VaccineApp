package com.si.VaccineApp.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Table(name = "appointments")
@Entity
public class Appointment {
    // in functie de numarul de vaccinuri disponibile
//prioritatea <= din profesie (medici, profesori, politie) si varsta, // nu o va vedea user-uil la conectare
//username,
//
//dataProgramarii,
//tipulVaccinului,
    @Id
    @GeneratedValue
    private UUID id;

    private int priority;
    private String username;
    private LocalDate dateForScheduling;
    private String producer;
}
