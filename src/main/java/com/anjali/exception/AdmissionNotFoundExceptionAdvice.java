package com.anjali.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AdmissionNotFoundExceptionAdvice   {
    @ResponseBody
    @ExceptionHandler(AdmissionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String admissionNotFoundHandler(AdmissionNotFoundException ex){
        return ex.getMessage();
    }

}
