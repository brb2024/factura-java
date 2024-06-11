package dao.mysql;

import dao.FacturaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Factura;

/**
 *
 * @author Usuario
 */
public class MySQLFacturaDAO implements FacturaDAO {

    private Connection con;
    private final String insertar = "INSERT INTO factura(fecha, cliente, total) VALUES(?, ?, ?);";
    private final String modificar = "UPDATE factura SET fecha = ?, cliente = ?, total = ? WHERE id_factura = ?;";
    private final String eliminar = "DELETE FROM factura WHERE id_factura = ?;";
    private final String obtenerTodos = "SELECT * FROM factura;";
    private final String buscar = "SELECT * FROM factura WHERE id_factura = ?;";

    public MySQLFacturaDAO(Connection con) {
        this.con = con;
    }

    @Override
    public boolean agregar(Factura a) {
        PreparedStatement stat = null;

        try {
            stat = con.prepareStatement(insertar);
            stat.setString(1, a.getFecha());
            stat.setString(2, a.getCliente());
            stat.setDouble(3, a.getTotal());
            stat.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            return false;
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    System.out.println("Error: " + e);
                }
            }
        }
    }

    @Override
    public boolean editar(Factura a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Factura> obtenerTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer obtener(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int obtenerSiguienteId() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        
        String query = "SELECT MAX(id_factura) FROM factura";
        int nextId = 1;
        try {
            stat = con.prepareStatement(query);
            rs = stat.executeQuery();
            
            if (rs.next()) {
                int maxId = rs.getInt(1);
                if (!rs.wasNull()) {
                    nextId = maxId + 1;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    System.out.println("Error: " + e);
                }
            }
        }
        
        return nextId;
    }

}
