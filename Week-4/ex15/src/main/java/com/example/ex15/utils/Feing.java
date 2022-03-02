package com.example.ex15.utils;

import com.example.ex15.Profesor.Infrastructure.controller.dto.output.ProfesorOutputDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="profesorFeign",url="http://localhost:8081/")
public interface Feing {
    @GetMapping("/profesor/{id}")
    ResponseEntity<ProfesorOutputDto> callServer(@PathVariable("id") Integer id);
}
