package com.anjali.exception;

public class AdmissionNotFoundException extends RuntimeException {
    public AdmissionNotFoundException(Long id){
        super("Could not find admission " + id);
    }

}
