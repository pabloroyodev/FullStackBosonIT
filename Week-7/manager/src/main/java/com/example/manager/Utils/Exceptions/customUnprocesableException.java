package com.example.manager.Utils.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class customUnprocesableException extends RuntimeException {

    public customUnprocesableException(String message) {
        super(message);
    }
}
