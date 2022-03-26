package com.example.ex27.Persona.Application;

import com.example.ex27.Persona.Infrastructure.controller.dto.input.PersonaInputDto;
import com.example.ex27.Persona.Infrastructure.controller.dto.output.PersonaOutputDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface PersonaService {
    List<PersonaOutputDto> getAllPersonas();
    PersonaOutputDto filterPersonaById(int id) throws Exception;
    UserDetails loadUserByUsername(String user);
    PersonaOutputDto addPersona(PersonaInputDto personaInputDto) throws Exception;
    PersonaOutputDto updatePersona(Integer id, PersonaInputDto personaInputDto) throws Exception;
    PersonaOutputDto updatePatchPersona(Integer id, PersonaInputDto personaInputDto) throws Exception;
    void deletePersona(Integer id) throws Exception;

}
