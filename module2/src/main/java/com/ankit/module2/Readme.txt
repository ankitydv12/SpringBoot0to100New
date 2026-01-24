Commit --->2.7.1 --> ControllerLevelExceptionHanding


/*
 * =====================================================
 * CONCEPT: EXCEPTION HANDLING IN SPRING BOOT (GLOBAL)
 * =====================================================
 *
 * Exception handling is the mechanism to:
 * - Catch runtime errors
 * - Convert them into meaningful HTTP responses
 * - Keep controllers clean and focused on business logic
 *
 * Instead of handling exceptions in every controller method,
 * Spring allows CENTRALIZED (GLOBAL) exception handling.
 */

/*
 * WHY GLOBAL EXCEPTION HANDLING IS NEEDED
 *
 * ❌ Without global handling:
 * - Repeated try-catch blocks in controllers
 * - Inconsistent error responses
 * - Poor API design
 *
 * ✔ With global handling:
 * - One place for all error logic
 * - Consistent error response structure
 * - Cleaner controllers
 *
 * INTERVIEW LINE:
 * "Global exception handling separates error concerns
 *  from business logic."
 */
/*
 * =====================================================
 * @RestControllerAdvice
 * =====================================================
 *
 * @RestControllerAdvice = @ControllerAdvice + @ResponseBody
 *
 * Meaning:
 * - Applies to ALL controllers globally
 * - Returns JSON responses (not views)
 *
 * Any exception thrown in controller/service
 * can be intercepted here.
 */
/*
 * =====================================================
 * @ExceptionHandler
 * =====================================================
 *
 * @ExceptionHandler(NoSuchElementException.class)
 *
 * Meaning:
 * - This method will be called automatically
 * - WHEN NoSuchElementException is thrown anywhere
 *
 * This exception usually occurs when:
 * - Optional.get() is called on empty Optional
 */
/*
 * METHOD: handleResourceNotFound
 *
 * public ResponseEntity<ApiError> handleResourceNotFound(...)
 *
 * Purpose:
 * - Catch exception
 * - Build a structured error response
 * - Return correct HTTP status
 */
/*
 * ApiError CONTENTS
 *
 * private HttpStatus status;
 * private String message;
 *
 * These fields describe:
 * - WHAT went wrong
 * - HOW serious it is (HTTP status)
 */
/*
 * =====================================================
 * ApiError ANNOTATIONS EXPLAINED
 * =====================================================
 */

/*
 * @Data (Lombok)
 *
 * Generates:
 * - getters
 * - setters
 * - toString()
 * - equals() & hashCode()
 *
 * Reduces boilerplate code.
 */

/*
 * @Builder (Lombok)
 *
 * Enables BUILDER PATTERN.
 *
 * Allows object creation like:
 * ApiError.builder()
 *        .status(...)
 *        .message(...)
 *        .build();
 *
 * This improves:
 * ✔ Readability
 * ✔ Immutability
 * ✔ Maintainability
 */
/*


