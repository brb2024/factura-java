package dao;

import java.util.List;

/**
 *
 * @author Usuario
 * @param <K>
 * @param <T>
 */
public interface DAO<K, T> {
    
    public boolean agregar(K a);
    public boolean editar(K a);
    public boolean eliminar(T id);
    public List<K> obtenerTodos();
    public T obtener(T id);
    
}
