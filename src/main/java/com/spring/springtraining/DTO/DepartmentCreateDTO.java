package com.spring.springtraining.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UniqueElements;

public record DepartmentCreateDTO(
        @UniqueElements
        @NotNull(message = "department cannot be null it required")
        @Size(min = 2, max = 50, message = "department must be between 2 and 50 characters")
        @NotBlank(message = "department cannot be blank it required")
        @jakarta.validation.constraints.Pattern(regexp = "[a-zA-Z]+", message = "department must contain only letters")
        String name) {
}
