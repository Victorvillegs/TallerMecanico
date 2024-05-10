package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.Map;
import java.util.Objects;

public enum TipoTrabajo {
    MECANICO("Mecánico"),
    REVISION("Revision");

     final String nombre;
    TipoTrabajo(String nombre){
        this.nombre = nombre;
    }
    public static TipoTrabajo get(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "El trabajo no puede ser nulo.");
        if (trabajo instanceof Revision) {
            return REVISION;
        } else {
            return MECANICO;
        }
    }
    public String toString(){
        return nombre;
    }
}
