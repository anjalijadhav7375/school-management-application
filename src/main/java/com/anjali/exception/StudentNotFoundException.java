package com.anjali.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;


public class StudentNotFoundException extends RuntimeException{

    public StudentNotFoundException(Long id){
        super("Could not found student " + id);
    }
}
