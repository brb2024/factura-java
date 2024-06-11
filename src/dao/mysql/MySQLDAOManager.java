package dao.mysql;

import dao.DAOManager;
import dao.DetalleFacturaDAO;
import dao.FacturaDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class MySQLDAOManager implements DAOManager {
    
    private Connection con;
    private FacturaDAO facturas = null;
    private DetalleFacturaDAO detalles = null;
    
    public MySQLDAOManager(String host, String puerto, String usuario, String pass, String base) throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://"+host+":"+puerto+"/"+base, usuario, pass);
    }

    @Override
    public FacturaDAO getFacturaDAO() {
        if(facturas == null) {
            facturas = new MySQLFacturaDAO(con);
        }
        
        return facturas;
    }

    @Override
    public DetalleFacturaDAO getDetalleFacturaDAO() {
        if(detalles == null) {
            detalles = new MySQLDetalleFacturaDAO(con);
        }
        
        return detalles;
    }
    
}
