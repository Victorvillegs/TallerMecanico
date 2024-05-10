package org.iesalandalus.programacion.tallermecanico.modelo.cascada;

import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.*;


import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class ModeloCascada implements Modelo {
    private IClientes clientes;
    private IVehiculos vehiculos;
    private ITrabajos trabajos;

    public ModeloCascada(FabricaFuenteDatos fabricaFuenteDatos) {
        IFuenteDatos fuenteDatos = fabricaFuenteDatos.crear();
        clientes = fuenteDatos.crearClientes();
        vehiculos = fuenteDatos.crearVehiculos();
        trabajos = fuenteDatos.crearTrabajos();
    }

    @Override
    public void comenzar() {
        clientes.comenzar();
        trabajos.comenzar();
        vehiculos.comenzar();
    }

    @Override
    public void terminar() {
        clientes.terminar();
        trabajos.terminar();
        vehiculos.terminar();
    }

    @Override
    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        clientes.insertar(new Cliente(cliente));

    }

    @Override
    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
        vehiculos.insertar(vehiculo);
    }

    @Override
    public void insertar(Trabajo trabajo) throws OperationNotSupportedException {
        Cliente clienteEncontrado = clientes.buscar(trabajo.getCliente());
        Vehiculo vehiculoEncontrado = vehiculos.buscar(trabajo.getVehiculo());
        if (trabajo instanceof Revision) {
            trabajos.insertar(new Revision(clienteEncontrado, vehiculoEncontrado, trabajo.getFechaInicio()));
        } else if (trabajo instanceof Mecanico) {
            trabajos.insertar(new Mecanico(clienteEncontrado, vehiculoEncontrado, trabajo.getFechaInicio()));
        }
    }

    @Override
    public Cliente buscar(Cliente cliente) {
        Objects.requireNonNull(clientes.buscar(cliente), "No existe el cliente buscado");
        return new Cliente(cliente);
    }

    @Override
    public Vehiculo buscar(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculos.buscar(vehiculo), "No existe el vehiculo buscado");
        return vehiculos.buscar(vehiculo);
    }

    @Override
    public Trabajo buscar(Trabajo trabajo) {
        Trabajo trabajoEncontrado = null;
        if (trabajo instanceof Mecanico) {
            trabajoEncontrado = new Mecanico((Mecanico) trabajos.buscar(trabajo));
        } else if (trabajo instanceof Revision) {
            trabajoEncontrado = new Revision((Revision) trabajos.buscar(trabajo));
        }
        return trabajoEncontrado;
    }

    @Override
    public boolean modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
        return clientes.modificar(cliente, nombre, telefono);
    }

    @Override
    public void anadirHoras(Trabajo trabajo, int horas) throws OperationNotSupportedException {
        trabajos.anadirHoras(trabajo, horas);
    }

    @Override
    public void anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws OperationNotSupportedException {
        trabajos.anadirPrecioMaterial(trabajo, precioMaterial);

    }

    public void cerrar(Trabajo trabajo, LocalDate fechaFin) throws OperationNotSupportedException {
        trabajos.cerrar(trabajo, fechaFin);
    }

    @Override
    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        for (Trabajo trabajo : trabajos.get(cliente)) {
            trabajos.borrar(trabajo);
        }
        clientes.borrar(cliente);
    }

    @Override
    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
        for (Trabajo trabajo : trabajos.get(vehiculo)) {
            trabajos.borrar(trabajo);
        }
        vehiculos.borrar(vehiculo);
    }

    @Override
    public void borrar(Trabajo trabajo) throws OperationNotSupportedException {
        trabajos.borrar(trabajo);
    }

    @Override
    public List<Cliente> getClientes() {
        List<Cliente> listaInstanciada = new ArrayList<>();
        for (Cliente cliente : clientes.get()) {
            listaInstanciada.add(new Cliente(cliente));
        }
        return listaInstanciada;
    }

    @Override
    public List<Vehiculo> getVehiculos() {
        return vehiculos.get();
    }

    @Override
    public List<Trabajo> getTrabajos() {
        List<Trabajo> listaInstanciada = new ArrayList<>();
        for (Trabajo trabajo : trabajos.get()) {
            if (trabajo instanceof Mecanico mecanico) {
                listaInstanciada.add(new Mecanico(mecanico));
            } else if (trabajo instanceof Revision revision) {
                listaInstanciada.add(new Revision(revision));
            }
        }
        return listaInstanciada;
    }

    @Override
    public List<Trabajo> getTrabajos(Cliente cliente) {
        List<Trabajo> listaInstanciada = new ArrayList<>();
        for (Trabajo trabajo : trabajos.get(cliente)) {
            if (trabajo instanceof Mecanico mecanico) {
                listaInstanciada.add(new Mecanico(mecanico));
            } else if (trabajo instanceof Revision revision) {
                listaInstanciada.add(new Revision(revision));
            }
        }
        return listaInstanciada;

    }

    @Override
    public List<Trabajo> getTrabajos(Vehiculo vehiculo) {
        List<Trabajo> listaInstanciada = new ArrayList<>();
        for (Trabajo trabajo : trabajos.get(vehiculo)) {
            if (trabajo instanceof Mecanico mecanico) {
                listaInstanciada.add(new Mecanico(mecanico));
            } else if (trabajo instanceof Revision revision) {
                listaInstanciada.add(new Revision(revision));
            }
        }
        return listaInstanciada;
    }

    @Override
    public Map<TipoTrabajo, Integer> getEstadisticasMensuales(LocalDate mes) {
        return trabajos.getEstadisticasMensuales(mes);
    }
}
