package com.example.ex24.Application;

import com.example.ex24.domain.Persona;
import com.example.ex24.infrastructure.controller.dto.input.PersonaInputDto;
import com.example.ex24.infrastructure.controller.dto.output.PersonaOutputDto;

import java.util.List;

public interface PersonaService {
    public List<PersonaOutputDto> getAllPersonas();
    public PersonaOutputDto filterPersonaById(String id) throws Exception;
    public List<Persona> filterPersonaByUser(String user);
    public PersonaInputDto addPersona(PersonaInputDto personaInputDto) throws Exception;
    public PersonaOutputDto updatePersona(String id, PersonaInputDto personaInputDto) throws Exception;
    public void deletePersona(String id) throws Exception;

}
