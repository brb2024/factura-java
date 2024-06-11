package dao;

import model.Factura;

/**
 *
 * @author Usuario
 */
public interface FacturaDAO extends DAO<Factura, Integer> {
    
    public int obtenerSiguienteId();
    
}
