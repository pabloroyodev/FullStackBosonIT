package com.example.ex13.StudentAsignatura.Application;

import com.example.ex13.StudentAsignatura.Domain.StudentAsignatura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAsignaturaService extends JpaRepository<StudentAsignatura, String> {
}
