package com.example.ex14.StudentAsignatura.Infrastructure.repository.jpa;

import com.example.ex14.StudentAsignatura.Domain.StudentAsignatura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAsignaturaRepositorio extends JpaRepository<StudentAsignatura, Integer> {
}
