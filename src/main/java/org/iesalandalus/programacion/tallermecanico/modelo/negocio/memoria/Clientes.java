package org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IClientes;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Clientes implements IClientes {
    private final List<Cliente> coleccionClientes;
    public Clientes() {
        this.coleccionClientes = new ArrayList<>();
    }
    public List<Cliente> get(){
        return new ArrayList<>(this.coleccionClientes);
    }

    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente,"No se puede insertar un cliente nulo.");
        if (buscar(cliente) != null){
            throw new OperationNotSupportedException("Ya existe un cliente con ese dni");
        }
        coleccionClientes.add(cliente);
    }

    public boolean modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException{
        Objects.requireNonNull(cliente,"No se puede modificar un cliente nulo.");
        Cliente c = buscar(cliente);
        if (c == null) throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        if (nombre != null){
            c.setNombre(nombre);
        }
        if (telefono != null){
            c.setTelefono(telefono);
        }
        return (nombre != null || telefono != null);
    }

    public Cliente buscar(Cliente cliente) {
        Objects.requireNonNull(cliente, "No se puede buscar un cliente nulo.");
        for (Cliente c : coleccionClientes){
            if (c.equals(cliente)){
                return c;
            }
        }
        return null;
    }

    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede borrar un cliente nulo.");
        Cliente c = buscar(cliente);
        if (c == null) throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        this.coleccionClientes.remove(c);
    }
}
