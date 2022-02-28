package com.example.ex13.Profesor.Infrastructure.repository.jpa;

import com.example.ex13.Profesor.Domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepositorio extends JpaRepository<Profesor, Integer> {
}
