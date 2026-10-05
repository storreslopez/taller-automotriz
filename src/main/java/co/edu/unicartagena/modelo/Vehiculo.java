package co.edu.unicartagena.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un vehículo asociado a un cliente.
 * 
 * @author Santiago Torres
 */
public class Vehiculo {
    private String placa;
    private String marca;
    private String modelo;
    private int anio;
    private int kilometraje;
    private List<OrdenServicio> ordenes;

    public Vehiculo(String placa, String marca, String modelo, int anio, int kilometraje) {
        if (placa == null || placa.trim().isEmpty() || marca == null || marca.trim().isEmpty() || modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException("Placa, marca y modelo son obligatorios");
        }
        if (anio <= 1885) {
            throw new IllegalArgumentException("El año debe ser valido");
        }
        if (kilometraje < 0) {
            throw new IllegalArgumentException("El kilometraje no puede ser negativo");
        }
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.kilometraje = kilometraje;
        this.ordenes = new ArrayList<>();
    }

    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAnio() {
        return anio;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        if (kilometraje < 0) {
            throw new IllegalArgumentException("El kilometraje no puede ser negativo");
        }
        this.kilometraje = kilometraje;
    }

    public List<OrdenServicio> getOrdenes() {
        return ordenes;
    }

    void registrarOrden(OrdenServicio o) {
        this.ordenes.add(o);
    }

    @Override
    public String toString() {
        return String.format("%s %s (%s)", marca, modelo, placa);
    }
}
