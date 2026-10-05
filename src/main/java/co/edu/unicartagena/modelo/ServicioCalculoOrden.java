package co.edu.unicartagena.modelo;

/**
 * Servicio encargado de calcular el valor final de una orden de servicio.
 * 
 * @author Santiago Torres
 */
public class ServicioCalculoOrden {

    public double calcularValorFinal(OrdenServicio orden, double porcentajeDescuento) {
        if (porcentajeDescuento < 0 || porcentajeDescuento > 100) {
            throw new IllegalArgumentException("El porcentaje de descuento debe estar entre 0 y 100");
        }
        
        double totalActividades = 0.0;
        for (Actividad actividad : orden.getActividades()) {
            totalActividades += actividad.getValorManoObra();
        }
        
        double descuento = totalActividades * (porcentajeDescuento / 100.0);
        double valorFinal = totalActividades - descuento;
        
        orden.actualizarTotal(valorFinal);
        return valorFinal;
    }
}
