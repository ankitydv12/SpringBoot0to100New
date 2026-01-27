Commit --->2.7.3 -->InterWorkingOfExcp|Exception|MethodArgumentNotValidException
/*
 * ============================================================
 * OVERALL IDEA: HOW EXCEPTION HANDLING IS DESIGNED
 * ============================================================
 *
 * Goal of exception handling in Spring Boot:
 * - Stop application from crashing
 * - Convert Java exceptions into MEANINGFUL HTTP responses
 * - Keep controllers & services clean
 *
 * Your design follows INDUSTRY-STANDARD layered exception handling.
 */
/*
 * ============================================================
 * WHAT NEEDS TO BE CREATED (THEORY FIRST)
 * ============================================================
 *
 * To implement clean exception handling, we need:
 *
 * 1️⃣ Custom Exception classes
 *    → Represent business/domain errors
 *
 * 2️⃣ Error response model (ApiError)
 *    → Defines how errors look in API response
 *
 * 3️⃣ Global Exception Handler
 *    → Catches exceptions and converts them to ApiError
 *
 * 4️⃣ Global Response Wrapper (optional but professional)
 *    → Wraps ALL responses (success + error) in same structure
 */
/*
 * ============================================================
 * FILE 1: ApiError
 * ============================================================
 *
 * PURPOSE:
 * ApiError defines the STRUCTURE of an error response.
 *
 * It answers:
 * - What went wrong?
 * - What HTTP status applies?
 * - What are the detailed validation errors (if any)?
 *
 * ApiError is a PURE DATA CLASS (no logic).
 */
/*
 * ApiError contains:
 *
 * - HttpStatus status
 *   → Semantic meaning of the error (404, 400, 500)
 *
 * - String message
 *   → Human-readable summary
 *
 * - List<String> subError
 *   → Detailed errors (mostly validation failures)
 *
 * WHY THIS CLASS EXISTS:
 * ✔ Consistent error format
 * ✔ Frontend-friendly
 * ✔ Easy to extend (timestamp, errorCode, path)
 */
/*
 * ============================================================
 * FILE 2: ApiResponse<T>
 * ============================================================
 *
 * PURPOSE:
 * ApiResponse is a WRAPPER for ALL API responses.
 *
 * It ensures:
 * - Same JSON structure for success and error
 *
 * Example:
 * {
 *   "timeStamp": "...",
 *   "data": {...},
 *   "error": null
 * }
 *
 * OR
 *
 * {
 *   "timeStamp": "...",
 *   "data": null,
 *   "error": {...}
 * }
 */
/*
 * WHY ApiResponse IS USED:
 *
 * ✔ Frontend parses responses easily
 * ✔ No need to guess response shape
 * ✔ Professional API contract
 *
 * This is OPTIONAL but VERY GOOD practice.
 */
/*
 * ============================================================
 * FILE 3: GlobalExceptionHandler
 * ============================================================
 *
 * THIS IS THE CORE OF EXCEPTION HANDLING.
 *
 * @RestControllerAdvice means:
 * - This class listens to ALL controllers
 * - It intercepts exceptions globally
 * - It returns JSON responses
 */
/*
 * ROLE OF GlobalExceptionHandler:
 *
 * - Catch exceptions thrown from:
 *   ✔ Controller layer
 *   ✔ Service layer
 *
 * - Decide:
 *   ✔ HTTP status
 *   ✔ Error message
 *   ✔ Error structure (ApiError)
 */
/*
 * HOW IT WORKS INTERNALLY:
 *
 * 1️⃣ Exception is thrown (Service / Controller)
 * 2️⃣ DispatcherServlet catches it
 * 3️⃣ Spring scans @RestControllerAdvice
 * 4️⃣ Finds matching @ExceptionHandler method
 * 5️⃣ Calls that method automatically
 * 6️⃣ ApiError is built
 * 7️⃣ ResponseEntity<ApiError> is returned
 */
/*
 * WHAT EACH HANDLER METHOD REPRESENTS:
 *
 * @ExceptionHandler(ResourceNotFoundExcp.class)
 * → Handles business-level NOT FOUND errors (404)
 *
 * @ExceptionHandler(MethodArgumentNotValidException.class)
 * → Handles validation failures (400)
 *
 * @ExceptionHandler(Exception.class)
 * → Fallback handler for unexpected errors (500)
 *
 * ORDER MATTERS:
 * - Specific exceptions first
 * - Generic Exception last
 */
/*
 * ============================================================
 * FILE 4: GlobalResponseHandler
 * ============================================================
 *
 * PURPOSE:
 * This class wraps ALL successful responses automatically
 * into ApiResponse<T>.
 *
 * It implements ResponseBodyAdvice<Object>.
 */
/*
 * HOW GlobalResponseHandler WORKS:
 *
 * - Spring is about to send response body
 * - beforeBodyWrite() is invoked
 *
 * If response is already ApiResponse:
 *   → return as is
 *
 * Else:
 *   → wrap response inside new ApiResponse<>(body)
 */
/*
 * ============================================================
 * COMPLETE END-TO-END FLOW (MENTAL MODEL)
 * ============================================================
 *
 * Client Request
 *      ↓
 * Controller
 *      ↓
 * Service
 *      ↓
 * (Exception OR Success)
 *
 * IF SUCCESS:
 *   Controller returns data
 *   → GlobalResponseHandler wraps it in ApiResponse
 *
 * IF EXCEPTION:
 *   Exception thrown
 *   → GlobalExceptionHandler catches it
 *   → ApiError created
 *   → Returned to client
 */

/*
 * ============================================================
 * ONE SECTION: IMPLEMENTATION STEPS (ADVICES PACKAGE ONLY)
 * ============================================================
 *
 * 1) Create ApiError
 *    - Holds error details: HttpStatus, message, subError
 *    - Used by exception handlers to build consistent error bodies
 *
 * 2) Create ApiResponse<T>
 *    - Wraps all responses (success + error)
 *    - Adds timestamp and keeps a uniform API contract
 *
 * 3) Create GlobalExceptionHandler (@RestControllerAdvice)
 *    - Central place to catch exceptions
 *    - Add @ExceptionHandler methods for:
 *        a) ResourceNotFoundExcp → 404
 *        b) MethodArgumentNotValidException → 400
 *        c) Exception (fallback) → 500
 *    - Build ApiError and return ResponseEntity<ApiError>
 *
 * 4) Create GlobalResponseHandler (ResponseBodyAdvice)
 *    - Wraps successful responses into ApiResponse<T>
 *    - Leaves already-wrapped responses unchanged
 *
 * 5) Throw exceptions from Controller/Service
 *    - Do not handle errors locally
 *    - Let advices translate exceptions into HTTP responses
 */

