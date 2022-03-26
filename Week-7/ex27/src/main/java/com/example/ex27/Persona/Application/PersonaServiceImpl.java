package com.example.ex27.Persona.Application;

import com.example.ex27.Persona.Infrastructure.controller.dto.input.PersonaInputDto;
import com.example.ex27.Persona.Infrastructure.controller.dto.output.PersonaOutputDto;
import com.example.ex27.Persona.Infrastructure.repository.jpa.PersonaRepositorio;
import com.example.ex27.Persona.Domain.Persona;
import com.example.ex27.exceptions.NotFoundException;
import com.example.ex27.exceptions.UnprocesableException;
import com.example.ex27.utils.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaServiceImpl implements PersonaService, UserDetailsService {

    @Autowired
    PersonaRepositorio personaRepositorio;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<PersonaOutputDto> getAllPersonas() {
        List<Persona> personas = personaRepositorio.findAll();
        List<PersonaOutputDto> personasOutputDto = personas.stream().map(p -> new PersonaOutputDto(p)).collect(Collectors.toList());
        return personasOutputDto;
    }
    
    @Override
    public PersonaOutputDto filterPersonaById(int id) throws Exception {
        Persona persona =
                personaRepositorio.findById(id).orElseThrow(() -> new NotFoundException(id + " not found."));
        PersonaOutputDto personaOutputDto = new PersonaOutputDto(persona);
        return personaOutputDto;
    }

    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
        List<Persona> personas = personaRepositorio.findByUser(user);
        if (personas.size() == 0) throw new NotFoundException(user + "not found.");
        Persona persona = personas.get(0);

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if(persona.getAdmin()){
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        }else{
            authorities.add(new SimpleGrantedAuthority("USER"));
        }

        return new org.springframework.security.core.userdetails.User(persona.getUser(), persona.getPassword(), authorities);
    }

    @Override
    public PersonaOutputDto addPersona(PersonaInputDto personaInputDto) throws Exception {

        if (utils.checkLengthUsr(personaInputDto)) {
            throw new Exception("La longitud del usuario ha de estar entre 6 y 10");
        }
        if (personaInputDto.getPassword() == null || personaInputDto.getName() == null || personaInputDto.getCompanyEmail() == null
            || personaInputDto.getPersonalEmail() == null || personaInputDto.getCity() == null || personaInputDto.getActive() == null
            || personaInputDto.getCreatedDate() == null) {
            throw new UnprocesableException("Faltan campos por introducir");
        }
        if (!personaRepositorio.findByUser(personaInputDto.getUser()).isEmpty()) {
            throw new UnprocesableException("El usuario ya existe");
        } else {
            Persona persona = personaInputDtoToEntity(personaInputDto);
            persona.setPassword(passwordEncoder.encode(persona.getPassword()));
            personaRepositorio.save(persona);
            PersonaOutputDto personaOutputDto = new PersonaOutputDto(persona);
            return personaOutputDto;
        }
    }

    @Override
    public PersonaOutputDto updatePersona(Integer id, PersonaInputDto personaInputDto) throws Exception {
        Persona persona =
                personaRepositorio
                        .findById(id)
                        .orElseThrow(() -> new UnprocesableException(id + " not found"));

        if (personaInputDto.getUser() != null) {
            if (utils.checkLengthUsr(personaInputDto)) {
                throw new UnprocesableException("La longitud del usuario no está entre 6 y 10");
            }

            persona.setUser(personaInputDto.getUser());
            persona.setPassword(passwordEncoder.encode(persona.getPassword()));
            persona.setName(personaInputDto.getName());
            persona.setSurname(personaInputDto.getSurname());
            persona.setCompanyEmail(personaInputDto.getCompanyEmail());
            persona.setPersonalEmail(personaInputDto.getPersonalEmail());
            persona.setCity(personaInputDto.getCity());
            persona.setActive(personaInputDto.getActive());
            persona.setCreatedDate(personaInputDto.getCreatedDate());
            persona.setImageUrl(personaInputDto.getImageUrl());
            persona.setTerminationDate(personaInputDto.getTerminationDate());
        }

        personaRepositorio.save(persona);
        PersonaOutputDto personaOutputDto = new PersonaOutputDto(persona);
        return personaOutputDto;
    }

    @Override
    public PersonaOutputDto updatePatchPersona(Integer id, PersonaInputDto personaInputDto) throws Exception {
        Persona persona =
                personaRepositorio
                        .findById(id)
                        .orElseThrow(() -> new UnprocesableException(id + " not found"));

        if (personaInputDto.getUser() != null) {
        if (utils.checkLengthUsr(personaInputDto)) {
                throw new UnprocesableException("La longitud del usuario no está entre 6 y 10");
            }
            persona.setUser(personaInputDto.getUser());
        }

        if (personaInputDto.getPassword() != null) {
            persona.setPassword(passwordEncoder.encode(persona.getPassword()));
        }

        if (personaInputDto.getName() != null) {
            persona.setName(personaInputDto.getName());
        }

        if (personaInputDto.getSurname() != null) {
            persona.setSurname(personaInputDto.getSurname());
        }

        if (personaInputDto.getCompanyEmail() != null) {
            persona.setCompanyEmail(personaInputDto.getCompanyEmail());
        }

        if (personaInputDto.getPersonalEmail() != null) {
            persona.setPersonalEmail(personaInputDto.getPersonalEmail());
        }

        if (personaInputDto.getCity() != null) {
            persona.setCity(personaInputDto.getCity());
        }

        if (personaInputDto.getActive() != null) {
            persona.setActive(personaInputDto.getActive());
        }

        if (personaInputDto.getCreatedDate() != null) {
            persona.setCreatedDate(personaInputDto.getCreatedDate());
        }

        if (personaInputDto.getImageUrl() != null) {
            persona.setImageUrl(personaInputDto.getImageUrl());
        }

        if (personaInputDto.getTerminationDate() != null) {
            persona.setTerminationDate(personaInputDto.getTerminationDate());
        }

        personaRepositorio.save(persona);
        PersonaOutputDto personaOutputDto = new PersonaOutputDto(persona);
        return personaOutputDto;
    }

    @Override
    public void deletePersona(Integer id) throws Exception {
        personaRepositorio.delete(
                personaRepositorio
                        .findById(id)
                        .orElseThrow(() -> new UnprocesableException(id + " not found")));
    }

    private Persona personaInputDtoToEntity(PersonaInputDto personaInputDto) {
        Persona persona = new Persona();
        persona.setUser(personaInputDto.getUser());
        persona.setPassword(personaInputDto.getPassword());
        persona.setName(personaInputDto.getName());
        persona.setSurname(personaInputDto.getSurname());
        persona.setCompanyEmail(personaInputDto.getCompanyEmail());
        persona.setPersonalEmail(personaInputDto.getPersonalEmail());
        persona.setCity(personaInputDto.getCity());
        persona.setActive(personaInputDto.getActive());
        persona.setCreatedDate(personaInputDto.getCreatedDate());
        persona.setImageUrl(personaInputDto.getImageUrl());
        persona.setTerminationDate(personaInputDto.getTerminationDate());
        persona.setAdmin(personaInputDto.getAdmin());

        return persona;
    }
}
