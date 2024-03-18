package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;

import java.util.List;

public interface IClientes {
    public List<Cliente> get();
    public void insertar(Cliente cliente);
    public boolean modificar (Cliente cliente, String nommbre,String telefono);
    public Cliente buscar (Cliente cliente);
    public void borrar (Cliente cliente);
}
