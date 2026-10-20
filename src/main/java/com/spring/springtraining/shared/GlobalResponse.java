
package com.spring.springtraining.shared;

import lombok.Getter;

import java.util.List;

@Getter
public class GlobalResponse<T> {

    public static final String SUCCESS = "success";
    public static final String ERROR = "error";

    private final String Status;
    private final T data;
    private final List<ErrorItems> errors;

    public GlobalResponse(List<ErrorItems> errors) {
        this.Status = ERROR;
        this.data = null;
        this.errors = errors;
    }

    public GlobalResponse(T data) {
        this.Status = SUCCESS;
        this.data = data;
        this.errors = null;
    }

    private GlobalResponse(
            String status,
            T data,
            List<ErrorItems> errors) {
        this.Status = status;
        this.data = data;
        this.errors = errors;
    }

    public static GlobalResponse<Void> successMessage(String message) {
        return new GlobalResponse<>(
                SUCCESS,
                null,
                List.of(new ErrorItems(message))
        );
    }

    public record ErrorItems(String message) {
    }
}
