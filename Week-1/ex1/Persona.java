import java.util.Optional;

public class Persona {
    private String nombre;
    private String poblacion;
    private String edad;

    public Persona(String[] persona) {
        Optional<String> optName;
        Optional<String> Poblacion;
        Optional<String> Edad;

        try {
            optName = Optional.of(persona[0]);
        } catch(IndexOutOfBoundsException e){
            optName = Optional.of("Desconocido");
        }

        try {
            Poblacion = Optional.of(persona[1]);
        } catch (IndexOutOfBoundsException e){
            Poblacion = Optional.of("Desconocida");
        }

        try {
            Edad = Optional.of(persona[2]);
        } catch (IndexOutOfBoundsException e) {
            Edad = Optional.of("-1");
        }

        this.setNombre(optName.get());
        this.setPoblacion(Poblacion.orElse("Desconocida"));
        this.setEdad(Edad.get());
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return ("Nombre: " + nombre + ", Población: " + poblacion + ", Edad: " + edad);
    }

    public String toStringEdad() {
        return ("Nombre: " + nombre + ", Población: " + poblacion + ", Edad: desconocida");
    }
}