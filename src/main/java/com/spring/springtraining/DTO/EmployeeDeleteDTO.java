package com.spring.springtraining.DTO;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record EmployeeDeleteDTO(
        @NotBlank
        UUID id
) {
}
