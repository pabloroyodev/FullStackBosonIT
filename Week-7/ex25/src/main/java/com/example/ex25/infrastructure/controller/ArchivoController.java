package com.example.ex25.infrastructure.controller;

import com.example.ex25.application.ArchivoService;
import com.example.ex25.application.ArchivoStorage;
import com.example.ex25.domain.Archivo;
import com.example.ex25.infrastructure.controller.dto.output.ArchivoOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
public class ArchivoController {

    @Autowired
    ArchivoService archivoService;

    @Autowired
    ArchivoStorage archivoStorage;

    @GetMapping
    public List<ArchivoOutputDto> findAll() {
        return archivoService.findAll();
    }

    @PostMapping("upload")
    public Archivo add(@RequestParam() MultipartFile file) throws Exception {
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        archivoStorage.save(file);
        Archivo archivo = new Archivo(null,file.getOriginalFilename(),extension,new Date());
        archivoService.add(archivo);
        return archivo;
    }

    @GetMapping("download/{id}")
    public ResponseEntity<Resource> findById(@PathVariable Integer id) throws Exception {
        Archivo archivo = archivoService.findById(id);
        Resource res = archivoStorage.load(archivo.getNombre());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{nombre}/download")
    public ResponseEntity<Resource> findById(@PathVariable String nombre) throws Exception {
        Resource res = archivoStorage.load(nombre);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
