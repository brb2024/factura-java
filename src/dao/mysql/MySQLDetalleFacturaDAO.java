package dao.mysql;

import dao.DetalleFacturaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import model.DetalleFactura;

/**
 *
 * @author Usuario
 */
public class MySQLDetalleFacturaDAO implements DetalleFacturaDAO {
    
    private Connection con;
    private final String insertar = "INSERT INTO detalle_factura(id_factura, producto, cantidad, "
            + "precio_unitario, subtotal) VALUES(?, ?, ?, ?, ?);";
    private final String modificar = "UPDATE factura SET fecha = ?, cliente = ?, total = ? WHERE id_factura = ?;";
    private final String eliminar = "DELETE FROM factura WHERE id_factura = ?;";
    private final String obtenerTodos = "SELECT * FROM factura;";
    private final String buscar = "SELECT * FROM factura WHERE id_factura = ?;";

    public MySQLDetalleFacturaDAO(Connection con) {
        this.con = con;
    }

    @Override
    public boolean agregar(DetalleFactura a) {
        PreparedStatement stat = null;
        
        try {
            stat = con.prepareStatement(insertar);
            stat.setInt(1, a.getIdFactura());
            stat.setString(2, a.getProducto());
            stat.setInt(3, a.getCantidad());
            stat.setDouble(4, a.getPrecio_unitario());
            stat.setDouble(5, a.getSubtotal());
            stat.executeUpdate();
            return true;
        } catch(SQLException e) {
            System.out.println("Error: " + e);
            return false;
        } finally {
            if(stat != null) {
                try {
                    stat.close();
                } catch(SQLException e) {
                    System.out.println("Error: " + e);
                }
            }
        }
    }

    @Override
    public boolean editar(DetalleFactura a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DetalleFactura> obtenerTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer obtener(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
