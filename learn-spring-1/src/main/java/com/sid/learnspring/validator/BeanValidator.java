package com.sid.learnspring.validator;

import com.sid.learnspring.exceptions.ObjectNotValidException;
import jakarta.validation.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class BeanValidator<T> {

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
