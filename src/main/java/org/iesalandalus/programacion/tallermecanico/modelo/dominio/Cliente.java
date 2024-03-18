package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cliente {
    private static final String ER_NOMBRE = "^[A-Za-z-]+(?:[ '][A-Za-z-]+)*$\n";
    private static final String ER_DNI = "^[a-zA-Z0-9]{7}$\n";
    private static final String ER_TELEFONO ="^(?:(?:\\+|00)34[-.\\s]?)?(?:6\\d{8}|[89]\\d{8})$\n";
    private String nombre;
    private String dni;
    private String telefono;

    public Cliente(String nombre, String dni, String telefono) {
        setNombre(nombre);
        setDni(dni);
        setTelefono(telefono);
    }

    public Cliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "No es posible copiar un cliente nulo.");
        nombre = cliente.nombre;
        dni = cliente.dni;
        telefono = cliente.telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        Objects.requireNonNull(nombre, "El nombre no puede ser nulo.");
        if (!nombre.matches(ER_NOMBRE)) {
            throw new IllegalArgumentException("El nombre no tiene un formato válido.");
        }
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    private void setDni(String dni) {
        Objects.requireNonNull(dni, "El DNI no puede ser nulo.");
        if (!dni.matches(ER_DNI)) {
            throw new IllegalArgumentException("El DNI no tiene un formato válido.");
        }
        if (!comprobarLetraDni(dni)) {
            throw new IllegalArgumentException("La letra del DNI no es correcta.");
        }
        this.dni = dni;
    }

    private static  boolean comprobarLetraDni(String dni) {
        String letraDni = dni.substring(dni.length() - 1);
        int numeroDni = Integer.parseInt(dni.substring(0, dni.length() - 1));
        String[] letras = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
        return (letraDni.equals(letras[numeroDni % 23]));
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        Objects.requireNonNull(telefono, "El teléfono no puede ser nulo.");
        if (!telefono.matches(ER_TELEFONO)) {
            throw new IllegalArgumentException("El teléfono no tiene un formato válido.");
        }
        this.telefono = telefono;
    }

    public static Cliente get(String dni) {
        Objects.requireNonNull(dni,"El DNI no puede ser nulo.");
        if (!dni.matches(ER_DNI)){
            throw new IllegalArgumentException("El DNI no tiene un formato válido.");
        }
        if (!comprobarLetraDni(dni)) throw new IllegalArgumentException("La letra del DNI no es correcta.");
        return new Cliente("Dani", dni, "663420880");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(dni, cliente.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s)", this.nombre, this.dni, this.telefono);
    }
}