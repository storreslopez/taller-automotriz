package co.edu.unicartagena;

import co.edu.unicartagena.modelo.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Principal. Front con JOptionPane
 *
 * @author Santiago Torres
 */
public class Main {
    public static void main(String[] args) {
        // Set LookAndFeel to system default for a more modern appearance
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Ignore
        }
        
        List<Persona> personas = new ArrayList<>();
        List<Vehiculo> vehiculos = new ArrayList<>();
        List<OrdenServicio> ordenes = new ArrayList<>();
        
        // Some initial data to test without typing much
        Mecanico mecanicoDefault = new Mecanico("Carlos Perez", "Motor");
        Cliente clienteDefault = new Cliente("Ana Lopez", "555-1234", "ana@example.com");
        personas.add(mecanicoDefault);
        personas.add(clienteDefault);
        
        String welcome = "Taller Automotriz\nBienvenido al sistema de gestión.";
        JOptionPane.showMessageDialog(null, welcome, "Taller Automotriz", JOptionPane.PLAIN_MESSAGE);

        String[] options = {
            "Nuevo Cliente", 
            "Nuevo Mecánico", 
            "Nuevo Vehículo", 
            "Nueva Orden", 
            "Gestionar Orden",
            "Ver Personas", 
            "Salir"
        };
        
        while (true) {
            String title = "Menú Principal";
            int choice = JOptionPane.showOptionDialog(null, 
                    title, 
                    "Gestión del Taller",
                    JOptionPane.DEFAULT_OPTION, 
                    JOptionPane.PLAIN_MESSAGE, 
                    null, 
                    options, 
                    options[0]);

            if (choice == 6 || choice == JOptionPane.CLOSED_OPTION) {
                break;
            }

            switch (choice) {
                case 0:
                    crearCliente(personas);
                    break;
                case 1:
                    crearMecanico(personas);
                    break;
                case 2:
                    crearVehiculo(personas, vehiculos);
                    break;
                case 3:
                    crearOrden(personas, vehiculos, ordenes);
                    break;
                case 4:
                    gestionarOrden(ordenes);
                    break;
                case 5:
                    mostrarPersonas(personas);
                    break;
            }
        }
    }
    
    private static void crearCliente(List<Persona> personas) {
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        JTextField nombreField = new JTextField(15);
        JTextField telField = new JTextField(15);
        JTextField emailField = new JTextField(15);
        
        panel.add(new JLabel("Nombre del Cliente:"));
        panel.add(nombreField);
        panel.add(new JLabel("Teléfono:"));
        panel.add(telField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        
        while (true) {
            int option = JOptionPane.showConfirmDialog(null, panel, "Nuevo Cliente", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    Cliente c = new Cliente(nombreField.getText(), telField.getText(), emailField.getText());
                    personas.add(c);
                    JOptionPane.showMessageDialog(null, "Cliente registrado exitosamente.");
                    break;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                break;
            }
        }
    }
    
    private static void crearMecanico(List<Persona> personas) {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        JTextField nombreField = new JTextField(15);
        JTextField espField = new JTextField(15);
        
        panel.add(new JLabel("Nombre del Mecánico:"));
        panel.add(nombreField);
        panel.add(new JLabel("Especialidad:"));
        panel.add(espField);
        
        while (true) {
            int option = JOptionPane.showConfirmDialog(null, panel, "Nuevo Mecánico", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    Mecanico m = new Mecanico(nombreField.getText(), espField.getText());
                    personas.add(m);
                    JOptionPane.showMessageDialog(null, "Mecánico registrado exitosamente.");
                    break;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                break;
            }
        }
    }
    
    private static void crearVehiculo(List<Persona> personas, List<Vehiculo> vehiculos) {
        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
        JTextField placaField = new JTextField(15);
        JTextField marcaField = new JTextField(15);
        JTextField modeloField = new JTextField(15);
        JTextField anioField = new JTextField(15);
        JTextField kmField = new JTextField(15);
        
        JComboBox<Object> clienteCombo = new JComboBox<>();
        clienteCombo.addItem("Ninguno");
        for (Persona p : personas) {
            if (p instanceof Cliente) {
                clienteCombo.addItem(p);
            }
        }
        
        panel.add(new JLabel("Placa:"));
        panel.add(placaField);
        panel.add(new JLabel("Marca:"));
        panel.add(marcaField);
        panel.add(new JLabel("Modelo:"));
        panel.add(modeloField);
        panel.add(new JLabel("Año:"));
        panel.add(anioField);
        panel.add(new JLabel("Kilometraje:"));
        panel.add(kmField);
        panel.add(new JLabel("Propietario:"));
        panel.add(clienteCombo);
        
        while (true) {
            int option = JOptionPane.showConfirmDialog(null, panel, "Nuevo Vehículo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    Vehiculo v = new Vehiculo(
                        placaField.getText(), 
                        marcaField.getText(), 
                        modeloField.getText(), 
                        Integer.parseInt(anioField.getText()), 
                        Integer.parseInt(kmField.getText())
                    );
                    vehiculos.add(v);
                    
                    Object selected = clienteCombo.getSelectedItem();
                    Cliente selectedClient = (selected instanceof Cliente) ? (Cliente) selected : null;
                    
                    if (selectedClient != null) {
                        selectedClient.agregarVehiculo(v);
                        JOptionPane.showMessageDialog(null, "Vehículo registrado y asignado.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Vehículo registrado (sin propietario asignado).", "Info", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Error: El año y kilometraje deben ser numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                break;
            }
        }
    }
    
    private static void crearOrden(List<Persona> personas, List<Vehiculo> vehiculos, List<OrdenServicio> ordenes) {
        if (vehiculos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay vehículos registrados para crear una orden.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Mecanico[] mecanicos = personas.stream().filter(p -> p instanceof Mecanico).toArray(Mecanico[]::new);
        if (mecanicos.length == 0) {
            JOptionPane.showMessageDialog(null, "No hay mecánicos registrados para asignar la orden.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        JComboBox<Vehiculo> vehiculoCombo = new JComboBox<>(vehiculos.toArray(new Vehiculo[0]));
        JComboBox<Mecanico> mecanicoCombo = new JComboBox<>(mecanicos);
        JTextField motivoField = new JTextField(15);
        
        panel.add(new JLabel("Vehículo:"));
        panel.add(vehiculoCombo);
        panel.add(new JLabel("Mecánico:"));
        panel.add(mecanicoCombo);
        panel.add(new JLabel("Motivo de ingreso:"));
        panel.add(motivoField);
        
        while (true) {
            int option = JOptionPane.showConfirmDialog(null, panel, "Nueva Orden", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (option == JOptionPane.OK_OPTION) {
                Vehiculo selectedV = (Vehiculo) vehiculoCombo.getSelectedItem();
                Mecanico selectedM = (Mecanico) mecanicoCombo.getSelectedItem();
                String motivo = motivoField.getText();
                
                if (selectedV != null && selectedM != null && motivo != null && !motivo.trim().isEmpty()) {
                    try {
                        OrdenServicio orden = new OrdenServicio(selectedV, selectedM, LocalDateTime.now(), motivo);
                        ordenes.add(orden);
                        JOptionPane.showMessageDialog(null, "Orden creada exitosamente.\nID: " + orden.getId());
                        break;
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error al crear la orden: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                break;
            }
        }
    }
    
    private static void mostrarPersonas(List<Persona> personas) {
        if (personas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay personas registradas.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("Personas Registradas\n\n");
        for (Persona p : personas) {
            // Se evidencia el uso de métodos sobrescritos (polimorfismo)
            sb.append(p.mostrarDetalles()).append("\n\n");
        }
        
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        
        JOptionPane.showMessageDialog(null, scrollPane, "Detalles de Personas", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private static void gestionarOrden(List<OrdenServicio> ordenes) {
        if (ordenes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay órdenes registradas.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        OrdenServicio selectedO = (OrdenServicio) JOptionPane.showInputDialog(null, "Seleccione la Orden:", "Gestionar Orden", JOptionPane.QUESTION_MESSAGE, null, ordenes.toArray(), ordenes.get(0));
        if (selectedO == null) return;
        
        while (true) {
            String[] options = {"Iniciar", "Agregar Actividad", "Finalizar/Facturar", "Cancelar Orden", "Volver"};
            int choice = JOptionPane.showOptionDialog(null, 
                    "Gestión de Orden\nEstado actual: " + selectedO.getEstado(), 
                    "Gestionar Orden", 
                    JOptionPane.DEFAULT_OPTION, 
                    JOptionPane.PLAIN_MESSAGE, 
                    null, 
                    options, 
                    options[0]);
            
            if (choice == 4 || choice == JOptionPane.CLOSED_OPTION) {
                break;
            }
                    
            try {
                switch (choice) {
                    case 0:
                        selectedO.iniciar();
                        JOptionPane.showMessageDialog(null, "Orden iniciada.");
                        break;
                    case 1:
                        if (selectedO.getEstado() != EstadoOrden.EN_PROCESO) {
                            JOptionPane.showMessageDialog(null, "La orden debe estar en proceso (EN_PROCESO) para agregar actividades.", "Error", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                        JPanel actPanel = new JPanel(new GridLayout(3, 2, 5, 5));
                        JTextField descField = new JTextField(15);
                        JTextField valorField = new JTextField(15);
                        JTextField minsField = new JTextField(15);
                        
                        actPanel.add(new JLabel("Descripción:"));
                        actPanel.add(descField);
                        actPanel.add(new JLabel("Valor Mano de Obra:"));
                        actPanel.add(valorField);
                        actPanel.add(new JLabel("Minutos Empleados:"));
                        actPanel.add(minsField);
                        
                        while (true) {
                            int opt = JOptionPane.showConfirmDialog(null, actPanel, "Nueva Actividad", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                            if (opt == JOptionPane.OK_OPTION) {
                                try {
                                    double valor = Double.parseDouble(valorField.getText());
                                    int mins = Integer.parseInt(minsField.getText());
                                    selectedO.agregarActividad(descField.getText(), valor, mins);
                                    JOptionPane.showMessageDialog(null, "Actividad agregada.");
                                    break;
                                } catch (NumberFormatException ex) {
                                    JOptionPane.showMessageDialog(null, "Error: Valor y minutos deben ser numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                break;
                            }
                        }
                        break;
                    case 2:
                        if (selectedO.getEstado() != EstadoOrden.EN_PROCESO) {
                            JOptionPane.showMessageDialog(null, "La orden debe estar en proceso (EN_PROCESO) para finalizarse.", "Error", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                        String descStr = JOptionPane.showInputDialog(null, "Ingrese porcentaje de descuento (0-100):", "Finalizar Orden", JOptionPane.QUESTION_MESSAGE);
                        if (descStr != null) {
                            try {
                                double descuento = Double.parseDouble(descStr);
                                ServicioCalculoOrden calc = new ServicioCalculoOrden();
                                double total = calc.calcularValorFinal(selectedO, descuento);
                                selectedO.finalizar();
                                String msg = String.format("Orden finalizada.\nTotal a pagar: $%.2f", total);
                                JOptionPane.showMessageDialog(null, msg);
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Error: El descuento debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        break;
                    case 3:
                        selectedO.cancelar();
                        JOptionPane.showMessageDialog(null, "Orden cancelada.");
                        break;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
