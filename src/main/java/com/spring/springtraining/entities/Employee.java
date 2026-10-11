package com.spring.springtraining.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor

public class Employee {
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private LocalDate hireDate;
    private String position;
    private UUID id;
    private UUID departmentId;

}
