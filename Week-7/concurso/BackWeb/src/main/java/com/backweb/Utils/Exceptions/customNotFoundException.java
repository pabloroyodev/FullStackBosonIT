package com.backweb.Utils.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class customNotFoundException extends RuntimeException{

    //throw new customNotFoundException("Persona con id: "+ id + " no encontrado");
    public customNotFoundException(String message) {
        super(message);
    }
}
