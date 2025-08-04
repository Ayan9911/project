package com.benz.car.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class UserRequestDTO {
    private String name;
    private String dob;
    private BigDecimal salary;
    private int age;

    // Getters and Setters
}