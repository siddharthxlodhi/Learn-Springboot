package com.sid.springsecurity3.validator;

import com.sid.springsecurity3.exception.ObjectNotValidException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
@Component
public class ObjectValidator<T> {

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    public void validate(T objectToValidate) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(objectToValidate);
        if (!constraintViolations.isEmpty()) {
            List<String> list = constraintViolations.stream().map(ConstraintViolation::getMessage).toList();
            throw new ObjectNotValidException(list);
        }
    }
}
