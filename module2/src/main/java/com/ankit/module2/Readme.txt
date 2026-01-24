Commit --->2.7.3 -->InterWorkingOfExcp|Exception|MethodArgumentNotValidException
/*
 * ============================================================
 * WHAT HAPPENS INTERNALLY AFTER THROW
 * ============================================================
 *
 * Step-by-step INTERNAL FLOW:
 *
 * 1️⃣ Exception is thrown in Service or Controller
 *
 * 2️⃣ Spring DispatcherServlet catches it
 *    (central front controller of Spring MVC)
 *
 * 3️⃣ DispatcherServlet looks for:
 *    @ControllerAdvice / @RestControllerAdvice
 *
 * 4️⃣ It scans methods annotated with @ExceptionHandler
 *
 * 5️⃣ Finds matching handler for:
 *    ResourceNotFoundExcp
 */

/*
 * ============================================================
 * COMPLETE REQUEST–EXCEPTION–RESPONSE FLOW
 * ============================================================
 *
 * Client Request
 *      ↓
 * Controller
 *      ↓
 * Service
 *      ↓
 * throw ResourceNotFoundExcp
 *      ↓
 * DispatcherServlet
 *      ↓
 * GlobalExceptionHandling (@ExceptionHandler)
 *      ↓
 * ApiError.builder().build()
 *      ↓
 * ResponseEntity<ApiError>
 *      ↓
 * JSON Error Response to Client
 */
