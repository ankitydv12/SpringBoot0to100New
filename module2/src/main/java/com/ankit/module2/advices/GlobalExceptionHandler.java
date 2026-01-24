package com.ankit.module2.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(NoSuchElementException exception)
    {
        ApiError apiError = ApiError.builder().status(HttpStatus.NOT_FOUND).message("Resource not found").build();
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }
    /*  * =====================================================
     * apiError.builder() — HOW IT WORKS
     * =====================================================
     *
     * ApiError.builder()
     *
     * - Creates a BUILDER object (not ApiError directly)
     * - Builder holds temporary values
     */
    /*
     * .status(HttpStatus.NOT_FOUND)
     *
     * - Sets the status field in builder
     * - Does NOT create object yet
     */
    /*
     * .message("Resource not found")
     *
     * - Sets the message field
     */
    /*
     * .build()
     *
     * - Finally creates an ApiError object
     * - Immutable-style creation
     *
     * This avoids:
     * new ApiError()
     * setStatus()
     * setMessage()
     */
}
