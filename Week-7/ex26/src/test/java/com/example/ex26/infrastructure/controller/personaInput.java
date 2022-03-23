package com.example.ex26.infrastructure.controller;

import com.example.ex26.infrastructure.controller.dto.input.PersonaInputDto;
import com.example.ex26.infrastructure.controller.dto.output.PersonaOutputDto;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;

public class personaInput {
    public static void createPersona(PersonaInputDto personaInputDto, String add){
        personaInputDto.setUser("usr" + add);
        personaInputDto.setPassword("12345");
        personaInputDto.setName("Pablo");
        personaInputDto.setSurname("Royo");
        personaInputDto.setCity("Tudela");
        personaInputDto.setActive(true);
        personaInputDto.setPersonalEmail("pabloPersonal@sample.sample");
        personaInputDto.setCompanyEmail("pabloEmpresa@sample.sample");
        personaInputDto.setCreatedDate(LocalDate.now());
        personaInputDto.setTerminationDate(LocalDate.parse("2028-12-12"));
        personaInputDto.setImageUrl("Not Found");
    }

    public static void assertionsPersona(PersonaInputDto personaInputDto, PersonaOutputDto personaOutputDto){
        Assertions.assertEquals(personaInputDto.getUser(), personaOutputDto.getUser());
        Assertions.assertEquals(personaInputDto.getName(), personaOutputDto.getName());
        Assertions.assertEquals(personaInputDto.getSurname(), personaOutputDto.getSurname());
        Assertions.assertEquals(personaInputDto.getPassword(), personaOutputDto.getPassword());
        Assertions.assertEquals(personaInputDto.getCity(), personaOutputDto.getCity());
        Assertions.assertEquals(personaInputDto.getActive(), personaOutputDto.getActive());
        Assertions.assertEquals(personaInputDto.getPersonalEmail(), personaOutputDto.getPersonalEmail());
        Assertions.assertEquals(personaInputDto.getCompanyEmail(), personaOutputDto.getCompanyEmail());
        Assertions.assertEquals(personaInputDto.getImageUrl(), personaOutputDto.getImageUrl());
        Assertions.assertEquals(personaInputDto.getCreatedDate(), personaOutputDto.getCreatedDate());
        Assertions.assertEquals(personaInputDto.getTerminationDate(), personaOutputDto.getTerminationDate());
    }
}
