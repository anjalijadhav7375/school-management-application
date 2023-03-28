package com.anjali.exception;

public class AdmissionNotFoundException extends RuntimeException {
    public AdmissionNotFoundException(Long id){
        super("Cou;d not find admission " + id);
    }

}
