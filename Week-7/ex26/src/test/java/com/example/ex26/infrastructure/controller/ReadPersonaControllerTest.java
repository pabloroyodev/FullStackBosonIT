package com.example.ex26.infrastructure.controller;

import com.example.ex26.Application.PersonaService;
import com.example.ex26.domain.Persona;
import com.example.ex26.infrastructure.controller.dto.input.PersonaInputDto;
import com.example.ex26.infrastructure.controller.dto.output.PersonaOutputDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static com.example.ex26.infrastructure.controller.personaInput.assertionsPersona;
import static com.example.ex26.infrastructure.controller.personaInput.createPersona;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ReadPersonaControllerTest {

    @Autowired
    private PersonaService personaService;

    @BeforeAll
    void setUp() throws Exception {
        PersonaInputDto personaA = new PersonaInputDto();
        createPersona(personaA, "hola");
        personaService.addPersona(personaA);
    }

    @Test
    @DisplayName("Testing read all persons")
    void findAll() {
        personaService.getAllPersonas();
    }

    @Test
    @DisplayName("Testing read person by id")
    void getPersonaById() throws Exception {
        PersonaInputDto personaA = new PersonaInputDto();
        createPersona(personaA, "hola");
        assertionsPersona(personaA, personaService.filterPersonaById(2));
    }

    @Test
    @DisplayName("Testing read person by id (Throw Exception)")
    void getPersonaByIdThrowException() {
        Assertions.assertThrows(Exception.class, () -> {
            personaService.filterPersonaById(0);
        });
    }

    @Test
    @DisplayName("Testing read person by username")
    void getPersonaByUsuario() {
        personaService.filterPersonaByUser("usrhola");
    }

    @Test
    @DisplayName("Testing read person by username (Throw Exception)")
    void getPersonaByUsuarioThrowException() {
        Assertions.assertThrows(Exception.class, () -> {
            personaService.filterPersonaByUser("usrx");
        });
    }
}