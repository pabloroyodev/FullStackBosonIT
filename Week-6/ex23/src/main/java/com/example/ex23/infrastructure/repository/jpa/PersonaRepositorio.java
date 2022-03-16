package com.example.ex23.infrastructure.repository.jpa;

import com.example.ex23.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;
import java.util.List;

public interface PersonaRepositorio extends JpaRepository<Persona, Integer> {
    List<Persona> findByUser(String usuario);
    void deleteById(Integer id);
    public List<Persona> filterPersonaByCriteria(HashMap<String, Object> conditions);
}
