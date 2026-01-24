package com.ankit.module2.advices;

import com.ankit.module2.Exceptions.ResourceNotFoundExcp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.View;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final View error;

    public GlobalExceptionHandler(View error) {
        this.error = error;
    }

    @ExceptionHandler(ResourceNotFoundExcp.class)
    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundExcp exception)
    {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND) //set the status of the ApiError Class
                .message(exception.getMessage()) // set the message of the ApiError class
                .build(); // this will creat the object  of the api class
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleInternalServerError(Exception exception)
    {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValdationErrors(MethodArgumentNotValidException exception)
    {
        List<String> errors = exception.getBindingResult()//bind the result into one
                .getAllErrors()
                .stream()
                .map(error->error.getDefaultMessage())
                .collect(Collectors.toList());
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(errors.toString())
                .subError(errors)
                .build();
        return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
        /*
         * =========================================================
         * Method: handleValdationErrors
         * =========================================================
         *
         * This method handles VALIDATION FAILURES in Spring Boot.
         *
         * It is invoked automatically when:
         * - @Valid validation fails on a DTO
         * - Request reaches controller
         * - Validation happens BEFORE controller method execution
         */

        /*
         * =========================================================
         * WHAT IS MethodArgumentNotValidException
         * =========================================================
         *
         * MethodArgumentNotValidException is thrown by Spring WHEN:
         * - @Valid is used on @RequestBody or method parameter
         * - One or more validation annotations fail
         *
         * Example:
         * @NotBlank, @Email, @Min, custom validation, etc.
         *
         * IMPORTANT:
         * This exception is thrown BEFORE your controller logic runs.
         */
    }

    /*
     * =========================================================
     * INTERNAL FLOW (STEP-BY-STEP)
     * =========================================================
     *
     * 1️⃣ Client sends request
     * 2️⃣ JSON → DTO conversion
     * 3️⃣ @Valid triggers validation
     * 4️⃣ Validation fails
     * 5️⃣ Spring throws MethodArgumentNotValidException
     * 6️⃣ DispatcherServlet catches it
     * 7️⃣ GlobalExceptionHandler intercepts it
     * 8️⃣ handleValdationErrors() is called
     */


    /*
     * =========================================================
     * METHOD LOGIC EXPLANATION
     * =========================================================
     */

    /*
     * exception.getBindingResult()
     *
     * - Contains ALL validation results
     * - Both success and failure details
     * - Think of it as a validation report
     */

    /*
     * .getAllErrors()
     *
     * - Returns list of ALL validation errors
     * - Each error corresponds to one failed rule
     */

    /*
     * .stream()
     *
     * - Converts error list into stream
     * - Allows functional processing
     */

    /*
     * .map(error -> error.getDefaultMessage())
     *
     * - Extracts human-readable error message
     * - Message comes from validation annotation
     */

    /*
     * .collect(Collectors.toList())
     *
     * - Collects all messages into List<String>
     *
     * Example:
     * [
     *   "Name must not be blank",
     *   "Email must be valid"
     * ]
     */


    /*
     * =========================================================
     * WHY WE COLLECT ERRORS INTO A LIST
     * =========================================================
     *
     * Because:
     * ✔ One request can have MULTIPLE validation errors
     * ✔ Client should fix all errors in one go
     * ✔ Frontend needs structured error details
     */


    /*
     * =========================================================
     * ApiError CREATION IN THIS METHOD
     * =========================================================
     *
     * ApiError.builder()
     *  - Creates builder object
     *
     * .status(HttpStatus.INTERNAL_SERVER_ERROR)
     *  - Sets HTTP status
     *  - NOTE: In real projects → BAD_REQUEST (400) is better
     *
     * .message(errors.toString())
     *  - Summary of validation errors
     *
     * .subError(errors)
     *  - Detailed list of individual validation messages
     *
     * .build()
     *  - Final ApiError object is created
     */


    /*
     * =========================================================
     * WHY handleValdationErrors IS IMPORTANT
     * =========================================================
     *
     * ✔ Centralizes validation error handling
     * ✔ Prevents ugly default Spring error response
     * ✔ Makes API consumer-friendly
     * ✔ Keeps controllers clean
     *
     * INTERVIEW GOLD:
     * "Validation errors are client errors, not server errors."
     */


    /*
     * =========================================================
     * IMPORTANT INTERVIEW NOTES
     * =========================================================
     *
     * Q1: When is MethodArgumentNotValidException thrown?
     * A : When @Valid fails on request parameters or body.
     *
     * Q2: Why not catch this in controller?
     * A : Violates separation of concerns.
     *
     * Q3: Correct HTTP status for validation errors?
     * A : 400 BAD_REQUEST (not 500).
     */



}
