package com.example.ex15.Persona.Application;

import com.example.ex15.Persona.Infrastructure.controller.dto.input.PersonaInputDto;
import com.example.ex15.Persona.Infrastructure.controller.dto.output.PersonaOutputDto;

import java.util.List;

public interface PersonaService {
    List<PersonaOutputDto> getAllPersonas();
    PersonaOutputDto filterPersonaById(int id) throws Exception;
    List<PersonaOutputDto> filterPersonaByUser(String user);
    PersonaOutputDto addPersona(PersonaInputDto personaInputDto) throws Exception;
    PersonaOutputDto updatePersona(Integer id, PersonaInputDto personaInputDto) throws Exception;
    PersonaOutputDto updatePatchPersona(Integer id, PersonaInputDto personaInputDto) throws Exception;
    void deletePersona(Integer id) throws Exception;

}
