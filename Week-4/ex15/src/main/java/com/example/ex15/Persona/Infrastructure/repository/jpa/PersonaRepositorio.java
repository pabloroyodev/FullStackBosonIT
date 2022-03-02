package com.example.ex15.Persona.Infrastructure.repository.jpa;

import com.example.ex15.Persona.Domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonaRepositorio extends JpaRepository<Persona, Integer> {
    List<Persona> findByUser(String usuario);
    void deleteById(Integer id);
}
