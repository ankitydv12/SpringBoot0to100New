package com.ankit.module2.dto;


import com.ankit.module2.annotation.EmployeeRoleValidation;
import com.ankit.module2.annotation.PrimeNumbervalidation;
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

//    @Pattern(regexp = "^(ADMIN|USER)$",message = "Role of Employee can either be User or Admin")
    @EmployeeRoleValidation
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

    @PrimeNumbervalidation
    private Integer prime;

}
