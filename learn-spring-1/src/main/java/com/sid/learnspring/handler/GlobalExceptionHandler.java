package com.sid.learnspring.handler;

import com.sid.learnspring.exceptions.ObjectNotValidException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class  GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ObjectNotValidException.class)
    public ResponseEntity<?> handleObjectNotValidException(ObjectNotValidException e) {
//        return new ResponseEntity<>(String.join("\n", e.getErrors()), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(e.getErrors(), HttpStatus.BAD_REQUEST);
    }
}
