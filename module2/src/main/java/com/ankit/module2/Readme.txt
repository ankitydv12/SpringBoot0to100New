Commit --->2.4.6 -> Custom Validation

/*
 * =========================================
 * PART 1: STEPS TO CREATE A CUSTOM VALIDATION
 * =========================================
 *
 * Step 1️⃣: Create a custom annotation interface
 * - Use @interface
 * - Annotate it with @Constraint
 * - Define message, groups, payload
 *
 * Step 2️⃣: Create a Validator class
 * - Implement ConstraintValidator<A, T>
 * - A → Custom Annotation
 * - T → Data type to validate (String, Integer, etc.)
 *
 * Step 3️⃣: Write validation logic in isValid()
 *
 * Step 4️⃣: Use the custom annotation on DTO fields
 *
 * Step 5️⃣: Trigger validation using @Valid in Controller
 */

/*
 * =========================================
 * PART 2: EXPLANATION OF YOUR CUSTOM ANNOTATION
 * =========================================
 *
 * FILE: EmployeeRoleValidation
 */
 /*
  * @Retention(RetentionPolicy.RUNTIME)
  *
  * Means:
  * - This annotation is available at RUNTIME.
  *
  * WHY REQUIRED:
  * - Validation happens at runtime.
  * - Hibernate Validator reads annotations using reflection.
  *
  * INTERVIEW POINT:
  * If retention is not RUNTIME, validation will NOT work.
  */

/*
 * @Target({ElementType.FIELD, ElementType.PARAMETER})
 *
 * Means:
 * - Annotation can be applied on:
 *   ✔ Class fields
 *   ✔ Method parameters
 *
 * Example usage:
 * - DTO field
 * - Controller method parameter
 *
 * Without this:
 * - Annotation could be used at wrong places.
 */

/*
 * @Constraint(validatedBy = {EmployeeRoleValidator.class})
 *
 * MOST IMPORTANT PART
 *
 * Meaning:
 * - This annotation is a validation constraint.
 * - EmployeeRoleValidator contains the actual logic.
 *
 * Spring / Hibernate automatically links:
 * EmployeeRoleValidation → EmployeeRoleValidator
 */

/*
 * public @interface EmployeeRoleValidation
 *
 * This defines a CUSTOM annotation.
 *
 * Naming convention:
 * - Annotation name usually ends with Validation or Constraint.
 */

/*
 * String message() default "{Role of Employee is ADMIN or USER}";
 *
 * This is the DEFAULT error message.
 *
 * Used when validation fails.
 *
 * NOTE:
 * - Can be overridden at usage time.
 * - Supports i18n (message bundles).
 */
/*
 * Class<?>[] groups() default {};
 *
 * Used for GROUP-BASED validation.
 *
 * Advanced use case:
 * - Different validations for different scenarios
 *
 * INTERVIEW NOTE:
 * Rarely used by freshers but REQUIRED by Bean Validation spec.
 */
/*
 * Class<? extends Payload>[] payload() default {};
 *
 * Used to attach METADATA to validation errors.
 *
 * Mostly used by:
 * - Frameworks
 * - Logging / severity levels
 *
 * INTERVIEW TRUTH:
 * Usually left empty in real projects.
 */
/*
 * =========================================
 * VALIDATOR CLASS EXPLANATION
 * =========================================
 *
 * FILE: EmployeeRoleValidator
 */
/*
 * implements ConstraintValidator<EmployeeRoleValidation, String>
 *
 * Meaning:
 * - EmployeeRoleValidation → Annotation type
 * - String → Field type being validated
 *
 * If field type mismatches → validation error at runtime.
 */
/*
 * return inputString.equals("ADMIN") || inputString.equals("USER");
 *
 * Logic:
 * - Only allow ADMIN or USER roles.
 *
 * If returns:
 * ✔ true  → validation passes
 * ❌ false → validation fails
 */
/*
 * Alternative logic (commented):
 *
 * List<String> roles = List.of("USER","ADMIN");
 * return roles.contains(inputString);
 *
 * This is MORE SCALABLE and readable.
 */


