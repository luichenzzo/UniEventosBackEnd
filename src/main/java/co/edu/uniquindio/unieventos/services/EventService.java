package co.edu.uniquindio.unieventos.services;

import co.edu.uniquindio.unieventos.model.document.Event;
import co.edu.uniquindio.unieventos.model.enums.EventStatus;
import co.edu.uniquindio.unieventos.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    // Obtener todos los eventos
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Obtener evento por ID
    public Optional<Event> getEventById(String id) {
        return eventRepository.findById(id);
    }

    // Guardar o actualizar un evento
    public Event saveOrUpdateEvent(Event event) {
        return eventRepository.save(event);
    }

    // Eliminar un evento por ID
    public void deleteEvent(String id) {
        eventRepository.deleteById(id);
    }

    // Buscar eventos por nombre
    public List<Event> getEventsByName(String name) {
        return eventRepository.findByName(name);
    }

    // Buscar eventos por ciudad
    public List<Event> getEventsByCity(String city) {
        return eventRepository.findByCity(city);
    }

    // Buscar eventos por fecha
    public List<Event> getEventsByDate(String date) {
        return eventRepository.findByDate(date);
    }

    // Buscar eventos por estado
    public List<Event> getEventsByStatus(EventStatus status) {
        return eventRepository.findByEventStatus(status);
    }

    // Buscar eventos combinados por nombre y ciudad
    public List<Event> getEventsByNameAndCity(String name, String city) {
        return eventRepository.findByNameAndCity(name, city);
    }

    // Buscar eventos combinados por nombre o ciudad
    public List<Event> getEventsByNameOrCity(String name, String city) {
        return eventRepository.findByNameOrCity(name, city);
    }

    // Buscar eventos combinados por fecha y estado
    public List<Event> getEventsByDateAndStatus(String date, EventStatus status) {
        return eventRepository.findByDateAndEventStatus(date, status);
    }
}

