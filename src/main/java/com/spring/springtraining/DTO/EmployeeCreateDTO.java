package com.spring.springtraining.DTO;

import jakarta.validation.constraints.*;

import java.time.LocalDate;


public record EmployeeCreateDTO(
        @NotNull(message = "Firstname cannot be null it required")
        @Size(min = 2, max = 50, message = "Firstname must be between 2 and 50 characters")
        @NotBlank(message = "Firstname cannot be blank it required")
        @jakarta.validation.constraints.Pattern(regexp = "[a-zA-Z]+", message = "Firstname must contain only letters")
        String firstname,

        @NotNull(message = "Lastname cannot be blank it required")
        @Size(min = 2, max = 50, message = "Lastname must be between 2 and 50 characters")
        String lastname,

        @NotBlank(message = "Email cannot be blank it required")
        @Email(message = "Email must be valid")
        String email,

        @NotBlank(message = "Phone number cannot be blank it required")
        @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
        String phoneNumber,

        @PastOrPresent(message = "Hire date must be in the past or present")
        LocalDate hireDate,

        @NotBlank(message = "Position cannot be blank it required")
        @Size(min = 2, max = 50, message = "Position must be between 2 and 50 characters")
        String position
) {

}
