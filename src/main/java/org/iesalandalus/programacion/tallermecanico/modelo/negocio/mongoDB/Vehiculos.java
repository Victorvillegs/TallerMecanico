package org.iesalandalus.programacion.tallermecanico.modelo.negocio.mongoDB;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IVehiculos;

import javax.naming.OperationNotSupportedException;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.mongodb.client.model.Filters.*;

public class Vehiculos implements IVehiculos {

    private static final String MATRICULA = "matricula";
    private static final String MARCA = "marca";
    private static final String MODELO = "modelo";
    private static final String COLECCION = "vehiculos";

    private Connection conexion;
    private static Vehiculos instancia;
    private MongoCollection<Document> coleccionVehiculos;

    static Vehiculos getInstancia() {
        if (instancia == null) {
            instancia = new Vehiculos();
        }
        return instancia;
    }

    private Vehiculos() {

    }
    @Override
    public void comenzar() {
        coleccionVehiculos = MongoDB.getBD().getCollection(COLECCION);
    }

    @Override
    public void terminar() {
        MongoDB.cerrarConexion();
    }

    @Override
    public List<Vehiculo> get() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        for (Document documento : coleccionVehiculos.find()) {
            vehiculos.add(getVehiculo(documento));
        }
        return vehiculos;
    }
    private Bson getCriterioBusqueda(Vehiculo vehiculo) {
        return eq(MATRICULA, vehiculo.matricula());
    }
    @Override
    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
        comprobarTrabajo(vehiculo.getCliente(), vehiculo.getVehiculo(), vehiculo.getFechaInicio());
        coleccionVehiculos.insertOne(getDocumento(vehiculo));
    }

    private void comprobarTrabajo(Vehiculo vehiculo) throws OperationNotSupportedException {
        Bson filtro = and(getCriterioBusqueda(vehiculo), exists(FECHA_FIN, false));
        if (coleccionTrabajos.find(filtro).first() != null) {
            throw new OperationNotSupportedException("El cliente tiene otro trabajo en curso.");
        }
        filtro = and(getCriterioBusqueda(vehiculo), exists(FECHA_FIN, false));
        if (coleccionTrabajos.find(filtro).first() != null) {
            throw new OperationNotSupportedException("El vehículo está actualmente en el taller.");
        }
        
    }

    @Override
    public Vehiculo buscar(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "No se puede buscar un vehiculo nulo.");
        return getVehiculo(coleccionVehiculos.find( (vehiculo)).first());
    }

    @Override
    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
        Objects.requireNonNull(vehiculo, "No se puede borrar un trabajo nulo.");
        DeleteResult resultado = coleccionVehiculos.deleteOne(getCriterioBusqueda(vehiculo));
        if (resultado.getDeletedCount() == 0) {
            throw new OperationNotSupportedException("No existe ningún trabajo igual.");
        }
    }

    public Vehiculo getVehiculo(Document document) {
        String matricula = document.getString(MATRICULA);
        String marca = document.getString(MARCA);
        String modelo = document.getString(MODELO);
        return new Vehiculo(marca, modelo, matricula);
    }

    Document getDocumento(Vehiculo vehiculo) {
        Document documento = null;
        if (vehiculo != null) {
            String marca = vehiculo.marca();
            String modelo = vehiculo.modelo();
            String matricula = vehiculo.matricula();
            documento = new Document().append(MARCA, marca).append(MODELO, modelo).append(MATRICULA, matricula);
        }
        return documento;
    }
}