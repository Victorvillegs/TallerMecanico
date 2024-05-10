package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.TipoTrabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import javax.naming.OperationNotSupportedException;
import java.util.List;

import java.util.List;
import java.time.LocalDate;
import java.util.Map;

public interface ITrabajos {

List<Trabajo> get();

List<Trabajo> get(Cliente cliente);

List<Trabajo> get(Vehiculo vehiculo);

void insertar(Trabajo trabajo) throws OperationNotSupportedException;

void anadirHoras(Trabajo trabajo, int horas) throws OperationNotSupportedException;
void anadirPrecioMaterial(Trabajo trabajo, float precioMaterial)throws OperationNotSupportedException;

void cerrar(Trabajo trabajo, LocalDate fechaFin)throws OperationNotSupportedException;

Trabajo buscar(Trabajo trabajo);
void comenzar();
void terminar();
void borrar(Trabajo trabajo)throws OperationNotSupportedException;
Map<TipoTrabajo, Integer> getEstadisticasMensuales(LocalDate mes) ;


}
