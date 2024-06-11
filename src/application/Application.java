package application;

import controller.FacturaController;
import dao.mysql.MySQLDAOManager;
import java.sql.SQLException;


/**
 *
 * @author Usuario
 */
public class Application {
    
    public static void main(String[] args) throws SQLException {
        MySQLDAOManager man = new MySQLDAOManager("localhost", "3307", "root", "", "base_factura");
        FacturaController control = new FacturaController(man);
        
        control.iniciar();
    }
    
}
