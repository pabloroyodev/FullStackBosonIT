package com.example.ex14.Profesor.Infrastructure.repository.jpa;

import com.example.ex14.Profesor.Domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepositorio extends JpaRepository<Profesor, Integer> {
}
