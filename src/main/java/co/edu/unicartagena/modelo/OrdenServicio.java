package co.edu.unicartagena.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Representa una orden de servicio para un vehículo, a cargo de un mecánico.
 * 
 * @author Santiago Torres
 */
public class OrdenServicio {
    private final String id;
    private LocalDateTime fechaIngreso;
    private String motivo;
    private double valorTotal;
    private EstadoOrden estado;
    private Vehiculo vehiculo;
    private Mecanico mecanico;
    private List<Actividad> actividades;

    public OrdenServicio(Vehiculo vehiculo, Mecanico mecanico, LocalDateTime fechaIngreso, String motivo) {
        if (vehiculo == null || mecanico == null) {
            throw new IllegalArgumentException("Vehiculo y mecanico son obligatorios");
        }
        if (fechaIngreso == null) {
            throw new IllegalArgumentException("La fecha de ingreso es obligatoria");
        }
        if (motivo == null || motivo.trim().isEmpty()) {
            throw new IllegalArgumentException("El motivo es obligatorio");
        }
        this.id = UUID.randomUUID().toString();
        this.vehiculo = vehiculo;
        this.mecanico = mecanico;
        this.fechaIngreso = fechaIngreso;
        this.motivo = motivo;
        this.estado = EstadoOrden.RECIBIDA;
        this.valorTotal = 0.0;
        this.actividades = new ArrayList<>();
        
        this.vehiculo.registrarOrden(this);
    }

    public String getId() {
        return id;
    }


    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public String getMotivo() {
        return motivo;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public EstadoOrden getEstado() {
        return estado;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public Mecanico getMecanico() {
        return mecanico;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public void agregarActividad(String descripcion, double valorManoObra, int minutosEmpleados) {
        this.actividades.add(new Actividad(descripcion, valorManoObra, minutosEmpleados));
    }

    public void iniciar() {
        if (this.estado != EstadoOrden.RECIBIDA) {
            throw new IllegalStateException("Solo se puede iniciar una orden en estado RECIBIDA");
        }
        this.estado = EstadoOrden.EN_PROCESO;
    }

    public void finalizar() {
        if (this.estado != EstadoOrden.EN_PROCESO) {
            throw new IllegalStateException("Solo se puede finalizar una orden en estado EN_PROCESO");
        }
        this.estado = EstadoOrden.FINALIZADA;
    }

    public void cancelar() {
        if (this.estado == EstadoOrden.FINALIZADA) {
            throw new IllegalStateException("No se puede cancelar una orden que ya esta FINALIZADA");
        }
        this.estado = EstadoOrden.CANCELADA;
    }

    void actualizarTotal(double valor) {
        this.valorTotal = valor;
    }

    @Override
    public String toString() {
        return String.format("Orden: %s | Vehiculo: %s | Motivo: %s | Estado: %s", id.substring(0, 8), vehiculo.getPlaca(), motivo, estado);
    }
}
