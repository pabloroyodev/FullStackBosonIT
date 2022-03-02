package com.example.ex14.Student.Infrastructure.repository.jpa;

import com.example.ex14.Student.Domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepositorio extends JpaRepository<Student, Integer> {
}
