package com.orero.libraryapp.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Calendar;

public class YearValidator implements ConstraintValidator<Year, Integer> {

    @Override
    public void initialize(Year constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer year, ConstraintValidatorContext context) {
        // Allowing years from 1000 to the current year as an example
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return year != null && year >= 1000 && year <= currentYear;
    }
}