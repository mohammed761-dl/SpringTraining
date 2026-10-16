package com.spring.springtraining.entities;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor

public class Employee {
    @NotNull(message = "Firstname cannot be null it required")
    @Size(min = 2, max = 50, message = "Firstname must be between 2 and 50 characters")
    @NotBlank(message = "Firstname cannot be blank it required")
    @Pattern(regexp = "[a-zA-Z]+", message = "Firstname must contain only letters")
    private String firstname;
    @NotNull(message = "Lastname cannot be blank it required")
    @Size(min = 2, max = 50, message = "Lastname must be between 2 and 50 characters")
    private String lastname;
    @NotBlank(message = "Email cannot be blank it required")
    @Email(message = "Email must be valid")
    private String email;
    @NotBlank(message = "Phone number cannot be blank it required")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phoneNumber;
    @PastOrPresent(message = "Hire date must be in the past or present")
    private LocalDate hireDate;
    @NotBlank(message = "Position cannot be blank it required")
    @Size(min = 2, max = 50, message = "Position must be between 2 and 50 characters")
    private String position;
    
    private UUID id;
    private UUID departmentId;

}
