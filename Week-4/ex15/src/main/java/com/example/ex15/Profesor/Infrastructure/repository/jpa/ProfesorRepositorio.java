package com.example.ex15.Profesor.Infrastructure.repository.jpa;

import com.example.ex15.Profesor.Domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepositorio extends JpaRepository<Profesor, Integer> {
}
