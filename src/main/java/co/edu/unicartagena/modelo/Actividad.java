package co.edu.unicartagena.modelo;

/**
 * Representa una actividad realizada dentro de una orden de servicio.
 * 
 * @author Santiago Torres
 */
public class Actividad {
    private String descripcion;
    private double valorManoObra;
    private int minutosEmpleados;

    public Actividad(String descripcion, double valorManoObra, int minutosEmpleados) {
        if (valorManoObra < 0) {
            throw new IllegalArgumentException("El valor de la mano de obra no puede ser negativo");
        }
        this.descripcion = descripcion;
        this.valorManoObra = valorManoObra;
        this.minutosEmpleados = minutosEmpleados;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getValorManoObra() {
        return valorManoObra;
    }

    public int getMinutosEmpleados() {
        return minutosEmpleados;
    }
}
