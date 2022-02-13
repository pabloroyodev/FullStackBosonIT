package Persona;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Persona {
    String nombre;
    Integer edad;
    String ciudad;

    @Override
    public String toString() {
        return "Se ha creado una nueva Persona ->" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", ciudad='" + ciudad;
    }
}
