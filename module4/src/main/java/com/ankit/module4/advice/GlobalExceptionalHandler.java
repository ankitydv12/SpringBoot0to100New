package com.ankit.module4.advice;

import com.ankit.module4.Exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionalHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException exception)
    {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND) //set the status of the ApiError Class
                .message(exception.getMessage()) // set the message of the ApiError class
                .build(); // this will creat the object  of the api class
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }
}
