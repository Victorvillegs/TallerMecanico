package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import java.util.*;

public class GestorEventos {

    private final Map<Evento, List<ReceptorEventos>> receptores = new EnumMap<>(Evento.class); // Cada evento va a tener una lista de subscriptores

    public GestorEventos(Evento... eventos) {
        Objects.requireNonNull(eventos, "Se debe gestionar algun evento");
        for (Evento evento : eventos) {
            receptores.put(evento, new ArrayList<>());
        }
    }

    public void suscribir(ReceptorEventos receptorEventos, Evento... eventos) {
        Objects.requireNonNull(receptorEventos, "El receptor de eventos no puede ser nulo.");
        Objects.requireNonNull(eventos, "Te debes suscribir a algun evento.");
        for (Evento evento : eventos) {
            List<ReceptorEventos> suscriptores = receptores.get(evento);
            suscriptores.add(receptorEventos);

        }
    }

    public void desuscribir(ReceptorEventos receptorEventos, Evento... eventos) {
        Objects.requireNonNull(receptorEventos, "El receptor de eventos no puede ser nulo.");
        for (Evento evento : eventos) {
            List<ReceptorEventos> suscriptores = receptores.get(evento);
            suscriptores.remove(receptorEventos);
            receptores.replace(evento, suscriptores);
        }
    }

    public void notificar(Evento evento) {
        Objects.requireNonNull(evento, "El evento no puede ser nulo.");
        List<ReceptorEventos> usuarios = receptores.get(evento);
        for (ReceptorEventos receptorEventos : receptores.get(evento)) {
            receptorEventos.actualizar(evento);
        }
    }
}