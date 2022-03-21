package com.example.ex25.application;

import com.example.ex25.domain.Archivo;
import com.example.ex25.infrastructure.controller.dto.output.ArchivoOutputDto;
import com.example.ex25.infrastructure.repository.ArchivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArchivoServiceImpl implements ArchivoService {

    @Autowired
    ArchivoRepository archivoRepository;

    @Override
    public Archivo add(Archivo archivo) {
        archivoRepository.save(archivo);
        return archivo;
    }

    @Override
    public List<ArchivoOutputDto> findAll() {
        List<Archivo> archivos = archivoRepository.findAll();
        List<ArchivoOutputDto> archivoOutput = archivos.stream().map(a -> new ArchivoOutputDto(a)).collect(Collectors.toList());
        return archivoOutput;
    }

    @Override
    public Archivo findById(Integer id) throws Exception {
        return archivoRepository.findById(id).orElseThrow(()-> new Exception("File with id " + id + " not found."));
    }
}
