package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;

public class Mecanico extends Trabajo{

    private static final float FACTOR_HORA = 30;
    private static final float FACTOR_PRECIO_MATERIAL = 1.5f;

    private float precioMaterial;

    public Mecanico(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        super(cliente, vehiculo, fechaInicio);
    }

    public Mecanico(Mecanico mecanico) {
        super(mecanico);
        this.precioMaterial = mecanico.precioMaterial;
    }

    public float getPrecioMaterial() {
        return precioMaterial ;
    }

    public void anadirPrecioMaterial(float precioMaterial) throws OperationNotSupportedException {
        if(precioMaterial <= 0) throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        if(getFechaFin() != null) throw new OperationNotSupportedException("No se puede añadir precio del material, ya que el trabajo mecánico está cerrado.");
        this.precioMaterial += precioMaterial;
    }

    @Override
    public float getPrecioEspecifico() {
        return getHoras() * FACTOR_HORA + precioMaterial * FACTOR_PRECIO_MATERIAL;
    }

    public String toString() {
        if(!estaCerrado()) {
            return String.format("Mecánico -> %s - %s (%s - ): %d horas, %.2f € en material",
                    getCliente().toString(), getVehiculo().toString(),
                    getFechaInicio().format(Revision.FORMATO_FECHA), getHoras(), getPrecioMaterial());
        }
        return String.format("Mecánico -> %s - %s (%s - %s): %d horas, %.2f € en material, %.2f € total",
                getCliente().toString(), getVehiculo().toString(),
                getFechaInicio().format(Revision.FORMATO_FECHA),
                getFechaFin().format(Revision.FORMATO_FECHA), getHoras(),
                getPrecioMaterial(), getPrecio());
    }
}