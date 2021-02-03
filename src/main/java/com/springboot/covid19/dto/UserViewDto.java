package com.springboot.covid19.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserViewDto {

    private String name;
    private String cnp;
    private String profession;
    private int priority;
}
