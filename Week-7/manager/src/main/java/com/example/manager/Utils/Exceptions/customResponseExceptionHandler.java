package com.example.manager.Utils.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

public class customResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(customNotFoundException.class)
    public final ResponseEntity<CustomErrorMessage> handleNotFoundException(customNotFoundException ex, WebRequest request) {
        CustomErrorMessage exceptionResponse = new CustomErrorMessage(new Date(), HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<CustomErrorMessage>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(customUnprocesableException.class)
    public final ResponseEntity<CustomErrorMessage> handleUnprocesableException(customUnprocesableException ex, WebRequest request) {
        CustomErrorMessage exceptionResponse = new CustomErrorMessage(new Date(), HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage());
        return new ResponseEntity<CustomErrorMessage>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
