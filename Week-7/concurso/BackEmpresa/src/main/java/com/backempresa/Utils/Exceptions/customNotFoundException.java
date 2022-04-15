package com.backempresa.Utils.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class customNotFoundException extends RuntimeException{
    public customNotFoundException(String message) {
        super(message);
    }
}
