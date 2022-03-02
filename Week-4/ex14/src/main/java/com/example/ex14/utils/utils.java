package com.example.ex14.utils;

import com.example.ex14.Persona.Infrastructure.controller.dto.input.PersonaInputDto;

public class utils {
    public static boolean checkLengthUsr(PersonaInputDto personaInputDto){
        if (personaInputDto.getUser().length() > 6 && personaInputDto.getUser().length() < 10) {
            return false;
        } else {return true;}
    }
}
