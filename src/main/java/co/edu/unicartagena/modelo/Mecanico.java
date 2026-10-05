package co.edu.unicartagena.modelo;

/**
 * Representa un mecánico que trabaja en el taller.
 * 
 * @author Santiago Torres
 */
public class Mecanico extends Persona {
    private String especialidad;

    public Mecanico(String nombre, String especialidad) {
        super(nombre);
        if (especialidad == null || especialidad.trim().isEmpty()) {
            throw new IllegalArgumentException("La especialidad es obligatoria");
        }
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    @Override
    public String mostrarDetalles() {
        return String.format("Mecanico: %s | Especialidad: %s", getNombre(), especialidad);
    }
}
