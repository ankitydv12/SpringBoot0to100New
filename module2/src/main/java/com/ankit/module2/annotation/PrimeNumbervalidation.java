package com.ankit.module2.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Constraint(validatedBy = {PrimeValidiator.class} )
public @interface PrimeNumbervalidation {
    String message() default "{Role of Employee is ADMIN or USER}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
