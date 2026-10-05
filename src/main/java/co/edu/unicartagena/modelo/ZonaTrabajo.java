package co.edu.unicartagena.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una zona de trabajo dentro del taller, a la cual se pueden asignar mecánicos.
 * 
 * @author Santiago Torres
 */
public class ZonaTrabajo {
    private String nombre;
    private List<Mecanico> mecanicos;

    public ZonaTrabajo(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la zona de trabajo es obligatorio");
        }
        this.nombre = nombre;
        this.mecanicos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Mecanico> getMecanicos() {
        return mecanicos;
    }

    public void asignarMecanico(Mecanico m) {
        if (m == null) {
            throw new IllegalArgumentException("El mecanico no puede ser nulo");
        }
        this.mecanicos.add(m);
    }

    public void trasladarMecanico(Mecanico m, ZonaTrabajo destino) {
        if (m == null || destino == null) {
            throw new IllegalArgumentException("Mecanico y destino no pueden ser nulos");
        }
        if (this.mecanicos.remove(m)) {
            destino.asignarMecanico(m);
        }
    }
}
