package co.edu.unicartagena.modelo;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServicioCalculoOrdenTest {

    @Test
    public void testCalcularValorFinal() {
        Vehiculo vehiculo = new Vehiculo("ABC-123", "Toyota", "Corolla", 2020, 15000);
        Mecanico mecanico = new Mecanico("Juan", "Frenos");
        OrdenServicio orden = new OrdenServicio(vehiculo, mecanico, LocalDateTime.now(), "Mantenimiento");
        
        orden.agregarActividad("Cambio de pastillas", 100000.0, 60);
        orden.agregarActividad("Alineacion", 50000.0, 30);
        
        ServicioCalculoOrden servicio = new ServicioCalculoOrden();
        double valorFinal = servicio.calcularValorFinal(orden, 10.0); // 10% discount
        
        assertEquals(135000.0, valorFinal, 0.01);
        assertEquals(135000.0, orden.getValorTotal(), 0.01);
    }
    
    @Test
    public void testActividadValorManoObraNegativo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Actividad("Error", -10.0, 10);
        });
    }
}
