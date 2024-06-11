package model;

/**
 *
 * @author Usuario
 */
public class Factura {
    
    private int idFactura;
    private String fecha;
    private String cliente;
    private double total;
    
    public Factura() {
        
    }

    public Factura(String fecha, String cliente, double total) {
        this.fecha = fecha;
        this.cliente = cliente;
        this.total = total;
    }

    public Factura(int idFactura, String fecha, String cliente, double total) {
        this.idFactura = idFactura;
        this.fecha = fecha;
        this.cliente = cliente;
        this.total = total;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Factura{" + "idFactura=" + idFactura + ", fecha=" + fecha + ", cliente=" + cliente + ", total=" + total + '}';
    }
    
}
