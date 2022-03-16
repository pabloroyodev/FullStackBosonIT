package com.example.ex23.Application;

import com.example.ex23.infrastructure.controller.dto.input.PersonaInputDto;
import com.example.ex23.infrastructure.controller.dto.output.PersonaOutputDto;

import java.time.LocalDate;
import java.util.List;

public interface PersonaService {
    public List<PersonaOutputDto> getAllPersonas();
    public PersonaOutputDto filterPersonaById(int id) throws Exception;
    public List<PersonaOutputDto> filterPersonaByUser(String user);
    public List<PersonaOutputDto> filterPersonaByCriteria(String user, String name, String surname, LocalDate createdDate, String dateCondition, String order, Integer page);
    public PersonaOutputDto addPersona(PersonaInputDto personaInputDto) throws Exception;
    public PersonaOutputDto updatePersona(Integer id, PersonaInputDto personaInputDto) throws Exception;
    public PersonaOutputDto updatePatchPersona(Integer id, PersonaInputDto personaInputDto) throws Exception;
    public void deletePersona(Integer id) throws Exception;

}
