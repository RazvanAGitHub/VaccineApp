package com.springboot.covid19.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class UserViewDtoShort {

    private String name;
    private LocalDate appointment;
}
