package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;

public class Revision extends Trabajo{

    private static final float FACTOR_HORA = 35;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        super(cliente, vehiculo, fechaInicio);
    }

    public Revision(Revision revision) {
        super(revision);
    }

    @Override
    public float getPrecioEspecifico() {
        return getHoras() * FACTOR_HORA;
    }

    public String toString() {
        if(!estaCerrado()) {
            return String.format("Revisión -> %s - %s (%s - ): %d horas",
                    getCliente().toString(),getVehiculo().toString(),
                    getFechaInicio().format(Trabajo.FORMATO_FECHA), getHoras());
        }
        return String.format("Revisión -> %s - %s (%s - %s): %d horas, %.2f € total",
                getCliente().toString(), getVehiculo().toString(),
                getFechaInicio().format(Trabajo.FORMATO_FECHA),
                getFechaFin().format(Trabajo.FORMATO_FECHA), getHoras(),
                getPrecio());
    }



}