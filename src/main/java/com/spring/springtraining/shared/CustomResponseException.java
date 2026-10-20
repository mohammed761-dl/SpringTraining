package com.spring.springtraining.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomResponseException extends RuntimeException {

    private final int statusCode;

    public CustomResponseException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public static CustomResponseException ResourceNotFound(String message) {
        return new CustomResponseException(404, message);
    }

    public static CustomResponseException BadRequest(String message) {
        return new CustomResponseException(400, message);
    }
}
