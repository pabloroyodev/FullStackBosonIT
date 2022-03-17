package com.example.ex24.Profesor.Infrastructure.repository.jpa;

import com.example.ex24.Profesor.Domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepositorio extends JpaRepository<Profesor, Integer> {
}
