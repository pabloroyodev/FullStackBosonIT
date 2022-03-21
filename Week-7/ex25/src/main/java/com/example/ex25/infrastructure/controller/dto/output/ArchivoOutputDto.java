package com.example.ex25.infrastructure.controller.dto.output;

import com.example.ex25.domain.Archivo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class ArchivoOutputDto implements Serializable {
    private Integer id;
    private String nombre;
    private String tipo;
    private Date fechaSubida;

    public ArchivoOutputDto(Archivo archivo){
        setId(archivo.getId());
        setFechaSubida(archivo.getFechaSubida());
        setNombre(archivo.getNombre());
        setTipo(archivo.getTipo());
    }
}
