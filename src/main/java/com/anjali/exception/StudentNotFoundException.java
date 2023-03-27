package com.anjali.exception;

public class StudentNotFoundException extends RuntimeException{

    public StudentNotFoundException(Long id){
        super("Could not found student " + id);
    }
}
