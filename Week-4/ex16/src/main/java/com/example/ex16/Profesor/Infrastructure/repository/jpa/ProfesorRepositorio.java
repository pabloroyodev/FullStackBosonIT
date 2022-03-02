package com.example.ex16.Profesor.Infrastructure.repository.jpa;

import com.example.ex16.Profesor.Domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepositorio extends JpaRepository<Profesor, Integer> {
}
