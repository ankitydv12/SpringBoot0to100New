Commit --->2.4.5 -> Validation Annotation
/*
 * ================================
 * CONCEPT: Validation In SpringBoot
 * ================================

*Add dependencies to pom.xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
/*
 * ================================
 * WHAT IS VALIDATION IN SPRING BOOT
 * ================================
 *
 * Validation means:
 * Ensuring that incoming data from the client is
 * CORRECT, SAFE, and MEETS BUSINESS RULES
 * BEFORE it reaches the database or business logic.
 *
 * Example:
 * - name should not be null
 * - email should be valid
 * - age should be within a range
 *
 * Validation protects your application from:
 * ✔ Bad input
 * ✔ Invalid state
 * ✔ Database corruption
 */

/*
 * ================================
 * WHY VALIDATION IS REQUIRED
 * ================================
 *
 * Client input CANNOT be trusted.
 *
 * Even if:
 * - Frontend validates
 * - UI has restrictions
 *
 * Backend MUST validate again because:
 * - APIs can be called directly (Postman, curl)
 * - Frontend can be bypassed
 *
 * INTERVIEW LINE:
 * "Backend validation is mandatory even if frontend validates."
 */

/*
 * =========================================
 * WHICH LIBRARY DOES VALIDATION IN SPRING
 * =========================================
 *
 * Spring Boot uses:
 * ✔ Jakarta Bean Validation (JSR-380)
 *
 * Implementation provider:
 * ✔ Hibernate Validator
 *
 * IMPORTANT:
 * - Spring Boot does NOT implement validation itself
 * - It integrates Hibernate Validator automatically
 */

/*
 * =========================================
 * HOW VALIDATION WORKS INTERNALLY
 * =========================================
 *
 * 1️⃣ Client sends JSON request
 *
 * 2️⃣ Spring converts JSON → DTO using Jackson
 *
 * 3️⃣ Validation annotations on DTO are checked
 *
 * 4️⃣ If validation FAILS:
 *    → Spring throws MethodArgumentNotValidException
 *
 * 5️⃣ If validation PASSES:
 *    → Request reaches Controller / Service
 *
 * Validation happens BEFORE method execution.
 */

/*
 * =========================================
 * HOW VALIDATION IS TRIGGERED
 * =========================================
 *
 * Validation is triggered by:
 *
 * @Valid  (or @Validated)
 *
 * Example (conceptually):
 * public ResponseEntity<?> addEmployee(@Valid @RequestBody EmployeeDTO dto)
 *
 * @Valid tells Spring:
 * "Validate this object before method execution"
 */

/*
 * =========================================
 * WHERE VALIDATION IS APPLIED
 * =========================================
 *
 * ✔ DTO layer (BEST PRACTICE)
 * ❌ Entity layer (NOT recommended)
 *
 * WHY DTO?
 * - API-specific rules
 * - Different validations for different APIs
 * - Entity should remain persistence-focused
 */

/*
 * =========================================
 * COMMON VALIDATION ANNOTATIONS (AWARENESS)
 * =========================================
 *
 * @NotNull      → must not be null
 * @NotBlank    → not null + not empty + not whitespace
 * @Email       → valid email format
 * @Size        → length constraints
 * @Min / @Max  → numeric limits
 *
 * These come from:
 * jakarta.validation.constraints
 */

