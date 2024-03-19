package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;

import javax.naming.OperationNotSupportedException;
import java.util.List;

public interface IClientes {
    public List<Cliente> get();
    public void insertar(Cliente cliente) throws OperationNotSupportedException;
    public boolean modificar (Cliente cliente, String nommbre,String telefono) throws OperationNotSupportedException;
    public Cliente buscar (Cliente cliente);
    public void borrar (Cliente cliente) throws OperationNotSupportedException;
}
