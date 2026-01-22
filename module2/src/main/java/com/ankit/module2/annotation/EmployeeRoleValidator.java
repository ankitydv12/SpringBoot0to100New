package com.ankit.module2.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class EmployeeRoleValidator implements ConstraintValidator<EmployeeRoleValidation,String> {

    @Override
    public boolean isValid(String inputString, ConstraintValidatorContext constraintValidatorContext) {
        return inputString.equals("ADMIN") || inputString.equals("USER");
        // another logic
//        List<String> roles = List.of("USER","ADMIN");
//        return roles.contains(inputString);
    }
}
