package com.example.ex23.utils;

import com.example.ex23.infrastructure.controller.dto.input.PersonaInputDto;

public class utils {

    public static boolean checkLengthUsr(PersonaInputDto personaInputDto){
        if (personaInputDto.user().length() > 6 && personaInputDto.user().length() < 10) {
            return false;
        } else {return true;}
    }
}
