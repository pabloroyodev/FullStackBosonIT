package com.example.ex26.infrastructure.repository.jpa;

import com.example.ex26.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonaRepositorio extends JpaRepository<Persona, Integer> {
    List<Persona> findByUser(String usuario);
    void deleteById(Integer id);
}
