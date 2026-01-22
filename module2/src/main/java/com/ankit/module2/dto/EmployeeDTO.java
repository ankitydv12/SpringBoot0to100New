package com.ankit.module2.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/*
    *DTO stand for Data Transfer Object
    * This is POJO class
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class EmployeeDTO {
    private Long id;
    @NotBlank(message = "name can be empty")
    @Size(min = 3,max = 15,message = "No of character in name should be in range:[3:15]")
    private String name;
    @Email(message = "Enter a valid email")
    private String email;
    @Max(value = 80,message = "The age can not be above 80")
    @Min(value = 18,message = "The age can not below 18")
    @NotNull()
    private Integer age;

    @Pattern(regexp = "^(ADMIN|USER)$",message = "Role of Employee can either be User or Admin")
    private String role;

    @NotNull() @Positive(message = "Salary of Employee should be positive ")
    @Digits(integer = 6, fraction = 2 , message = "The Salary can be in the foam ")
    @DecimalMax(value = "100000.34")
    @DecimalMin(value = "100.5")
    private Double salary;

    @PastOrPresent(message = "DateOfJoining field is not in future")
    private LocalDate dateOfJoining;
    @AssertTrue(message = "This should be always active")
    @JsonProperty("isActive")
    private Boolean isActive;

    /*
     * ================================
     * VALIDATION ANNOTATIONS (SPRING BOOT)
     * ================================
     *
     * Validation annotations are used to enforce RULES on incoming data
     * before it reaches business logic or database.
     *
     * They are part of:
     * jakarta.validation (Bean Validation – JSR 380)
     *
     * Spring Boot uses Hibernate Validator as the implementation.
     */


    /*
     * WHERE VALIDATION ANNOTATIONS SHOULD BE USED
     *
     * ✔ DTO layer (BEST PRACTICE)
     * ❌ Entity layer (avoid in real projects)
     *
     * Reason:
     * - DTO represents API contract
     * - Entity represents database structure
     */


    /*
     * @NotNull
     *
     * - Value must NOT be null
     * - Allows empty values
     *
     * Use when:
     * - Field must exist in request
     */


    /*
     * @NotEmpty
     *
     * - Value must NOT be null
     * - Value must NOT be empty
     *
     * Applies to:
     * - String
     * - Collection
     * - Map
     */


    /*
     * @NotBlank
     *
     * - Value must NOT be null
     * - Must contain at least one non-whitespace character
     *
     * Best for:
     * - User input strings (name, role, username)
     */


    /*
     * @Size(min, max)
     *
     * - Enforces length constraints
     *
     * Example use:
     * - Password length
     * - Username length
     */


    /*
     * @Email
     *
     * - Ensures value follows email format
     *
     * IMPORTANT:
     * - Checks format only
     * - Does NOT verify if email exists
     */


    /*
     * @Min(value)
     *
     * - Sets minimum numeric value
     *
     * Used for:
     * - Age
     * - Quantity
     */


    /*
     * @Max(value)
     *
     * - Sets maximum numeric value
     *
     * Used with @Min for ranges
     */


    /*
     * @Positive / @PositiveOrZero
     *
     * - Enforces positive numbers
     *
     * Useful for:
     * - Salary
     * - Price
     */


    /*
     * @Past / @Future
     *
     * - Validates date fields
     *
     * @Past   → date must be in past
     * @Future → date must be in future
     */


    /*
     * HOW VALIDATION IS TRIGGERED
     *
     * - Add @Valid or @Validated in Controller
     *
     * Example:
     * public ResponseEntity<?> add(@Valid @RequestBody DTO dto)
     *
     * Validation happens BEFORE method execution.
     */


    /*
     * INTERVIEW GOLD POINTS
     *
     * ✔ Validation is declarative and annotation-based
     * ✔ Backend validation is mandatory
     * ✔ Hibernate Validator is default provider
     * ✔ DTO validation keeps architecture clean
     */


    /*
     * COMMON MISTAKE
     *
     * ❌ Relying only on frontend validation
     * ❌ Validating Entity instead of DTO
     * ❌ Ignoring validation error handling
     */

}
