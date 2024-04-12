package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import javax.naming.OperationNotSupportedException;

public abstract class Trabajo {

    private static final float FACTOR_DIA = 10;
    static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private Cliente cliente;
    private Vehiculo vehiculo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int horas;

    protected Trabajo(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio){
        setCliente(cliente);
        setVehiculo(vehiculo);
        Objects.requireNonNull(fechaInicio, "La fecha de inicio no puede ser nula.");
        if(fechaInicio.isAfter(LocalDate.now())) throw new IllegalArgumentException("La fecha de inicio no puede ser futura.");
        this.fechaInicio = fechaInicio;
    }

    protected Trabajo(Trabajo trabajo){
        Objects.requireNonNull(trabajo, "El trabajo no puede ser nulo.");
        this.cliente = new Cliente(trabajo.cliente);
        this.vehiculo = trabajo.vehiculo;
        this.fechaInicio = trabajo.fechaInicio;
        this.fechaFin = trabajo.fechaFin;
        this.horas = trabajo.horas;
    }

    public static Trabajo copiar(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "El trabajo no puede ser nulo.");
        if(trabajo instanceof Revision) {
            return new Revision((Revision) trabajo);
        }
        return new Mecanico((Mecanico) trabajo);
    }

    public static Trabajo get(Vehiculo vehiculo) {
        Trabajo t = new Revision(Cliente.get("12345678Z"), vehiculo, LocalDate.now());
        return t;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    private void setCliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return this.vehiculo;
    }

    private void setVehiculo(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getHoras() {
        return this.horas;
    }

    public void anadirHoras(int horas) throws OperationNotSupportedException {
        if(horas <= 0) throw new IllegalArgumentException("Las horas a añadir deben ser mayores que cero.");
        if(fechaFin != null) throw new OperationNotSupportedException("No se puede añadir horas, ya que el trabajo está cerrado.");
        this.horas += horas;
    }

    public boolean estaCerrado() {
        return this.fechaFin != null;
    }

    public void cerrar(LocalDate fechaFin) throws OperationNotSupportedException {
        Objects.requireNonNull(fechaFin, "La fecha de fin no puede ser nula.");
        if(fechaFin.isAfter(LocalDate.now())) throw new IllegalArgumentException("La fecha de fin no puede ser futura.");
        if(fechaFin.isBefore(fechaInicio)) throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        if(estaCerrado()) throw new OperationNotSupportedException("El trabajo ya está cerrado.");
        this.fechaFin = fechaFin;
    }

    public float getPrecio() {
        return getPrecioFijo() + getPrecioEspecifico();
    }

    private float getPrecioFijo() {
        return getDias() * FACTOR_DIA;
    }

    private float getDias() {
        if(fechaFin == null) return 0;
        return fechaInicio.until(fechaFin, ChronoUnit.DAYS);
    }

    public abstract float getPrecioEspecifico();

    @Override
    public int hashCode() {
        return Objects.hash(cliente, fechaInicio, vehiculo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Trabajo other = (Trabajo) obj;
        return Objects.equals(cliente, other.cliente) && Objects.equals(fechaInicio, other.fechaInicio)
                && Objects.equals(vehiculo, other.vehiculo);
    }


}