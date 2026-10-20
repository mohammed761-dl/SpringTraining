package com.spring.springtraining.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EmployeeUpdateDTO(
        @NotNull(message = "Firstname cannot be null it required")
        @Size(min = 2, max = 50, message = "Firstname must be between 2 and 50 characters")
        @NotBlank(message = "Firstname cannot be blank it required")
        @jakarta.validation.constraints.Pattern(regexp = "[a-zA-Z]+", message = "Firstname must contain only letters")
        String firstname,

        @NotNull(message = "Lastname cannot be blank it required")
        @Size(min = 2, max = 50, message = "Lastname must be between 2 and 50 characters")
        String lastname,


        @NotBlank(message = "Phone number cannot be blank it required")
        @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
        String phoneNumber,


        @NotBlank(message = "Position cannot be blank it required")
        @Size(min = 2, max = 50, message = "Position must be between 2 and 50 characters")
        String position
) {
}
