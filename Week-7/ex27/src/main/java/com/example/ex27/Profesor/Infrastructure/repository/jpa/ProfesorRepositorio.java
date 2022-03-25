package com.example.ex27.Profesor.Infrastructure.repository.jpa;

import com.example.ex27.Profesor.Domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepositorio extends JpaRepository<Profesor, Integer> {
}
