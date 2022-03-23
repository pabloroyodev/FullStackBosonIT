package com.example.ex26.infrastructure.controller;

import com.example.ex26.Application.PersonaService;
import com.example.ex26.infrastructure.controller.dto.input.PersonaInputDto;
import com.example.ex26.infrastructure.repository.jpa.PersonaRepositorio;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.ex26.infrastructure.controller.personaInput.createPersona;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DeletePersonaControllerTest {

    @Autowired
    private PersonaService personaService;

    @Autowired
    private PersonaRepositorio personaRepositorio;

    @BeforeAll
    void setUp() throws Exception {
        PersonaInputDto personaC = new PersonaInputDto();
        createPersona(personaC, "hola");
        personaService.addPersona(personaC);
    }

    @Test
    @DisplayName("Testing delete person by Id")
    void deletePersona() throws Exception {
        personaService.deletePersona(1);
    }

    //El Id no existe en la BD por lo que ha de saltar excepcion
    @Test
    @DisplayName("Testing delete person by not existing Id (Throw Exception)")
    void deletePersonaThrowException() {
        Assertions.assertThrows(Exception.class, () -> {
            personaService.deletePersona(0);
        });
    }
}