package co.edu.unicartagena.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un cliente del taller automotriz.
 * 
 * @author Santiago Torres
 */
public class Cliente extends Persona {
    private String telefono;
    private String email;
    private List<Vehiculo> vehiculos;

    public Cliente(String nombre, String telefono, String email) {
        super(nombre);
        if (telefono == null || telefono.trim().isEmpty() || email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Telefono y email son obligatorios");
        }
        this.telefono = telefono;
        this.email = email;
        this.vehiculos = new ArrayList<>();
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("Telefono es obligatorio");
        }
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email es obligatorio");
        }
        this.email = email;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void agregarVehiculo(Vehiculo v) {
        if (v == null) {
            throw new IllegalArgumentException("El vehiculo no puede ser nulo");
        }
        this.vehiculos.add(v);
    }

    @Override
    public String mostrarDetalles() {
        return String.format("Cliente: %s | Email: %s | Tel: %s", getNombre(), email, telefono);
    }
}
