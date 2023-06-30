package com.sitaindia.backend.Exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
    /*
     * As part of this project I didn't able to find Custom exception that is needed to be handle.
     * but for the Unknown Exception, i have handled it beacuse if any exception occur,then i get to know the errorMessage properly able to fix that exception.
     */
     @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handlerException(Exception ex) {
        final ExceptionResponse exceptionResponse = new ExceptionResponse("Unknown", ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
