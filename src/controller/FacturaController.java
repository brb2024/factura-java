package controller;

import dao.mysql.MySQLDAOManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import model.DetalleFactura;
import model.Factura;
import view.VistaDetalles;
import view.VistaFactura;

/**
 *
 * @author Usuario
 */
public class FacturaController {
    
    private final MySQLDAOManager manager;
    private VistaFactura vista;
    private VistaDetalles vistaDetalles;
    private DefaultTableModel modelo;
    private List<DetalleFactura> detalles;
    private int filaSeleccionada;
    
    public FacturaController(MySQLDAOManager manager) {
        this.manager = manager;
        modelo = new DefaultTableModel();
        this.detalles = new ArrayList<>();
        this.filaSeleccionada = 0;
    }
    
    public void iniciar() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            System.out.println("Error: " + e);
        }
        
        SwingUtilities.invokeLater(() -> {
            vista = new VistaFactura();
            vistaDetalles = new VistaDetalles();
            vista.setVisible(true);
            iniciarEventos();
            asignarId();
            obtenerFecha();
        });
    }
    
    public void iniciarEventos() {
        vista.btnNuevoDetalle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vistaDetalles.setVisible(true);
                vistaDetalles.campoIdFac.setText(vista.campoId.getText());
            }
        });
        
        vista.btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarFactura();
            }
        });
        
        vista.btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarDetalle();
            }
        });
        
        vistaDetalles.btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarDetalle();
            }
        });
        
        vista.tablaDetalles.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                int seleccion = vista.tablaDetalles.rowAtPoint(e.getPoint());
                
                filaSeleccionada = seleccion;
                vista.btnEliminar.setEnabled(true);
                //System.out.println(filaSeleccionada);
            }
        });
    }
    
    public void asignarId() {
        String id = manager.getFacturaDAO().obtenerSiguienteId()+"";
        vista.campoId.setText(id);
    }
    
    public void obtenerFecha() {
        LocalDate fecha = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaFormateada = fecha.format(formatter);
        vista.campoFecha.setText(fechaFormateada);
    }
    
    public void agregarDetalle() {
        DetalleFactura detalle = new DetalleFactura();
        String idFactura = vistaDetalles.campoIdFac.getText();
        String producto = vistaDetalles.campoProducto.getText();
        String cantidad = vistaDetalles.campoCantidad.getText();
        String precioUnitario = vistaDetalles.campoPrecioUnitario.getText();
        
        if(!idFactura.equals("") && !producto.equals("") && !cantidad.equals("") && !precioUnitario.equals("")) {
            detalle.setIdFactura(Integer.parseInt(idFactura));
            detalle.setProducto(producto);
            detalle.setCantidad(Integer.parseInt(cantidad));
            detalle.setPrecio_unitario(Double.parseDouble(precioUnitario));
            detalle.setSubtotal(Integer.parseInt(cantidad) * Double.parseDouble(precioUnitario));
        
            detalles.add(detalle);
            cargarTabla();
            calcularTotal();
            limpiarCampos();
            JOptionPane.showMessageDialog(null, "Datos agregados correctamente.", 
                    null, JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", 
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
        
    }
    
    public void guardarFactura() {
        Factura factura = new Factura();
        String fecha = vista.campoFecha.getText();
        String cliente = vista.campoCliente.getText();
        double total = Double.parseDouble(vista.campoTotal.getText());
        int id = Integer.parseInt(vista.campoId.getText());
        boolean facturaAgregada = false;
        boolean detallesAgregados = false;
        
        if(!cliente.equals("")) {
            factura.setIdFactura(id);
            factura.setFecha(fecha);
            factura.setCliente(cliente);
            factura.setTotal(total);
            
            if(manager.getFacturaDAO().agregar(factura)) {
                facturaAgregada = true;
            } else {
                facturaAgregada = false;
            }
            
            for(int i = 0; i < detalles.size(); i++) {
                if(manager.getDetalleFacturaDAO().agregar(detalles.get(i))) {
                    detallesAgregados = true;
                } else {
                    detallesAgregados = false;
                    break;
                }
            }
            
            if(facturaAgregada && detallesAgregados) {
                detalles.clear();
                cargarTabla();
                vista.campoCliente.setText("");
                vista.campoTotal.setText("0");
                System.out.println("");
                asignarId();
                JOptionPane.showMessageDialog(null, "Datos agregados correctamente.", 
                    null, JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } else {
            JOptionPane.showMessageDialog(null, "El campo cliente es obligatorio.", 
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void cargarTabla() {
        modelo = (DefaultTableModel)vista.tablaDetalles.getModel();
        modelo.setRowCount(0);
        Object[] fila = new Object[5];
        
        for(int i = 0; i < detalles.size(); i++) {
            fila[0] = detalles.get(i).getIdFactura();
            fila[1] = detalles.get(i).getProducto();
            fila[2] = detalles.get(i).getCantidad();
            fila[3] = detalles.get(i).getPrecio_unitario();
            fila[4] = detalles.get(i).getSubtotal();
            modelo.addRow(fila);
        }
        
        vista.tablaDetalles.setModel(modelo);
    }
    
    public void eliminarDetalle() {
        int opcion = JOptionPane.showConfirmDialog(null, "¿Borrar este producto?", "Borrar", JOptionPane.YES_NO_OPTION);
        
        if(opcion == JOptionPane.YES_OPTION) {
            detalles.remove(filaSeleccionada);
        
            cargarTabla();
            calcularTotal();
            vista.btnEliminar.setEnabled(false);
            System.out.println(detalles);
        }
        
        vista.btnEliminar.setEnabled(false);
        vista.tablaDetalles.clearSelection();
        
    }
    
    public void limpiarCampos() {
        vistaDetalles.campoProducto.setText("");
        vistaDetalles.campoCantidad.setText("");
        vistaDetalles.campoPrecioUnitario.setText("");
    }
    
    public void calcularTotal() {
        double precioTotal = 0;
        
        for(int i = 0; i < detalles.size(); i++) {
            precioTotal += detalles.get(i).getPrecio_unitario() * detalles.get(i).getCantidad();
        }
        
        vista.campoTotal.setText((precioTotal+""));
    }
    
}
