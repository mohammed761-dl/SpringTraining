package com.spring.springtraining.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DepartmentCreateDTO(

        @NotBlank(message = "Department name is required")

        @Size(
                min = 1,
                max = 50,
                message = "Department must be between 1 and 50 characters"
        )

        @Pattern(
                regexp = "[a-zA-Z]+",
                message = "Department must contain only letters"
        )

        String name

) {
}
