package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros.FuenteDatosFicheros;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.mariaDB.FabricaFuenteDatosMariaDB;

public enum FabricaFuenteDatos {
    FICHEROS {
        @Override
        public IFuenteDatos crear() {
            return new FuenteDatosFicheros();
        }
    },MARIADB {
        @Override
        public IFuenteDatos crear() {
            return new FabricaFuenteDatosMariaDB();
        }
    };

    public abstract IFuenteDatos crear();
}

