package com.example.ex24.StudentAsignatura.Infrastructure.repository.jpa;

import com.example.ex24.StudentAsignatura.Domain.StudentAsignatura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAsignaturaRepositorio extends JpaRepository<StudentAsignatura, Integer> {
}
