package com.ankit.module2.advices;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ApiError {
    private HttpStatus status;
    private String message;
    private List<String> subError;

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getSubError() {
        return subError;
    }

    public void setSubError(List<String> subError) {
        this.subError = subError;
    }
}
