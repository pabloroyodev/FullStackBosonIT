package com.example.ex25.infrastructure.repository;

import com.example.ex25.domain.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchivoRepository extends JpaRepository<Archivo, Integer> {
}
