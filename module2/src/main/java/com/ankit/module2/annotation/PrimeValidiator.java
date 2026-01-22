package com.ankit.module2.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PrimeValidiator implements ConstraintValidator<PrimeNumbervalidation,Integer> {
    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        if(integer<=1) return false;
        if(integer==2) return true;
        if(integer%2==0) return false;
        for (int i = 3; i * i <= integer/2; i+=2) {
            if(integer%i==0) return false;
        }
        return true;
    }
}
