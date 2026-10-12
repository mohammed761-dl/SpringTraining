package com.spring.springtraining.shared;

import lombok.Getter;

import java.util.*;

@Getter
public class GlobalResponse<T> {
    public final static String SUCCESS = "success";
    public final static String ERROR = "error";

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
    public record ErrorItems(String message) {
    }

}
