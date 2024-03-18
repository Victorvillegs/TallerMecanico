package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.util.List;

import java.util.List;
import java.time.LocalDate;

public interface ITrabajos {

List<Trabajo> get();

Trabajo get(Cliente cliente);

Trabajo get(Vehiculo vehiculo);

void insertar(Trabajo trabajo);

void anadirHoras(Trabajo trabajo, int horas);
void anadirPrecioMaterial(Trabajo trabajo, float precioMaterial);

void cerrar(Trabajo trabajo, LocalDate fechaFin);

Trabajo buscar(Trabajo trabajo);

void borrar(Trabajo trabajo);
}
