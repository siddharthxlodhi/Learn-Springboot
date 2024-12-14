package com.sid.springsecurity3.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ObjectNotValidException extends RuntimeException {

    private List<String> errors;
}
