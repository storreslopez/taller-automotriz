package co.edu.unicartagena.modelo;

import java.util.UUID;

/**
 * Representa una persona genérica en el sistema.
 * 
 * @author Santiago Torres
 */
public abstract class Persona {
    private final String id;
    private String nombre;

    protected Persona(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacio");
        }
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract String mostrarDetalles();

    @Override
    public String toString() {
        return nombre;
    }
}
