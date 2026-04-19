package com.ankit.module4.advice;

import lombok.Data;
import org.apache.logging.log4j.CloseableThreadContext;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {
    private LocalDateTime timeStamp;
    private String error;
    private HttpStatus status;

    public ApiError()
    {
        timeStamp = LocalDateTime.now();
    }

    public ApiError(String error, HttpStatus status) {
        this();
        this.error = error;
        this.status = status;
    }
}
