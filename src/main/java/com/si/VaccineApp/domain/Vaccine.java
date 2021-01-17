package com.si.VaccineApp.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "vaccines")
@Entity
public class Vaccine {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String producer;
    @Column(nullable = false)
    private int storageTemperature;
    @Column(nullable = false)
    private int rappel;
    @Column(nullable = false)
    private int minAge;
    @Column(nullable = false)
    private int priority1AmountInitial;
    @Column(nullable = false)
    private int priority1AmountRemaining;
    @Column(nullable = false)
    private int priority2AmountInitial;
    @Column(nullable = false)
    private int priority2AmountRemaining;
    @Column(nullable = false)
    private int priority3AmountInitial;
    @Column(nullable = false)
    private int priority3AmountRemaining;

}
