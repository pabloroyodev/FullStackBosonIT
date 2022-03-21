package com.example.ex25.application;

import com.example.ex25.domain.Archivo;
import com.example.ex25.infrastructure.controller.dto.output.ArchivoOutputDto;

import java.util.List;

public interface ArchivoService {
    Archivo add(Archivo archivo);
    List<ArchivoOutputDto> findAll();
    Archivo findById(Integer id) throws Exception;
}
