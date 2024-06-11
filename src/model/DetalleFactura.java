package model;

/**
 *
 * @author Usuario
 */
public class DetalleFactura {
    
    private int idDetalle;
    private int idFactura;
    private String producto;
    private int cantidad;
    private double precio_unitario;
    private double subtotal;
    
    public DetalleFactura() {
        
    }

    public DetalleFactura(int idFactura, String producto, int cantidad, double precio_unitario, double subtotal) {
        this.idFactura = idFactura;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
        this.subtotal = subtotal;
    }

    public DetalleFactura(int idDetalle, int idFactura, String producto, int cantidad, double precio_unitario, double subtotal) {
        this.idDetalle = idDetalle;
        this.idFactura = idFactura;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
        this.subtotal = subtotal;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "DetalleFactura{" + "idDetalle=" + idDetalle + ", idFactura=" + idFactura + ", producto=" + producto + ", cantidad=" + cantidad + ", precio_unitario=" + precio_unitario + ", subtotal=" + subtotal + '}';
    }
    
}
