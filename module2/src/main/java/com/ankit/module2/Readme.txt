Commit --->2.4.4 -> ResponseEntity|StatusCode
/*
 * ================================
 * CONCEPT: ResponseEntity
 * ================================
 *
 * ResponseEntity represents the COMPLETE HTTP response.
 *
 * It allows you to control:
 * ✔ Response body (DTO / data)
 * ✔ HTTP status code (200, 201, 404, etc.)
 * ✔ HTTP headers (if needed)
 *
 * Without ResponseEntity:
 * - Spring always returns 200 OK by default
 * - You lose control over HTTP semantics
 *
 * That is NOT acceptable in real REST APIs.
 */

/*
 * WHY WE USE ResponseEntity
 *
 * REST APIs are NOT just about data,
 * they are about COMMUNICATING STATE to the client.
 *
 * Example:
 * - Data found        → 200 OK
 * - Data created      → 201 CREATED
 * - Data not found    → 404 NOT FOUND
 * - Invalid request   → 400 BAD REQUEST
 *
 * ResponseEntity lets us explicitly tell the client
 * WHAT happened, not just return data blindly.
 */

/*
 * ============================================
 * BIG PICTURE (INTERVIEW CRITICAL)
 * ============================================
 *
 * Controller responsibility:
 * ✔ Handle HTTP
 * ✔ Return ResponseEntity
 * ✔ Map service result → HTTP status
 *
 * Service responsibility:
 * ✔ Business logic
 * ✔ DTO ↔ Entity conversion
 *
 * Repository responsibility:
 * ✔ DB access
 *
 * ResponseEntity keeps your API:
 * - Correct
 * - Predictable
 * - Professional
 */

