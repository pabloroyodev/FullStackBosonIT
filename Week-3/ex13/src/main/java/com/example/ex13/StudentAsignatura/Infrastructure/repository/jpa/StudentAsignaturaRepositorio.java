package com.example.ex13.StudentAsignatura.Infrastructure.repository.jpa;

import com.example.ex13.StudentAsignatura.Domain.StudentAsignatura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAsignaturaRepositorio extends JpaRepository<StudentAsignatura, Integer> {
}
