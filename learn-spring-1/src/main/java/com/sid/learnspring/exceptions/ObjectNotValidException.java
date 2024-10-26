package com.sid.learnspring.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
public class ObjectNotValidException extends RuntimeException {

    private List<String> errors;

}
