package co.edu.uniquindio.unieventos.controller;

import co.edu.uniquindio.unieventos.model.document.Event;
import co.edu.uniquindio.unieventos.model.enums.EventStatus;
import co.edu.uniquindio.unieventos.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/get_all")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    // Obtener un evento por ID
    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Event>> getEventById(@PathVariable String id) {
        Optional<Event> event = eventService.getEventById(id);
        return ResponseEntity.ok(event);
    }

    // Crear o actualizar un evento
    @PostMapping("/save")
    public ResponseEntity<Event> saveEvent(@RequestBody Event event) {
        Event savedEvent = eventService.saveOrUpdateEvent(event);
        return ResponseEntity.ok(savedEvent);
    }

    // Eliminar un evento por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable String id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok().build();
    }

    // Buscar eventos por nombre
    @GetMapping("/find_by_name/{name}")
    public ResponseEntity<List<Event>> getEventsByName(@PathVariable String name) {
        List<Event> events = eventService.getEventsByName(name);
        return ResponseEntity.ok(events);
    }

    // Buscar eventos por ciudad
    @GetMapping("/find_by_city/{city}")
    public ResponseEntity<List<Event>> getEventsByCity(@PathVariable String city) {
        List<Event> events = eventService.getEventsByCity(city);
        return ResponseEntity.ok(events);
    }

    // Buscar eventos por fecha
    @GetMapping("/find_by_date/{date}")
    public ResponseEntity<List<Event>> getEventsByDate(@PathVariable String date) {
        List<Event> events = eventService.getEventsByDate(date);
        return ResponseEntity.ok(events);
    }

    // Buscar eventos por estado
    @GetMapping("/find_by_status/{status}")
    public ResponseEntity<List<Event>> getEventsByStatus(@PathVariable EventStatus status) {
        List<Event> events = eventService.getEventsByStatus(status);
        return ResponseEntity.ok(events);
    }

    // Buscar eventos por nombre y ciudad
    @GetMapping("/find_by_name_and_city")
    public ResponseEntity<List<Event>> getEventsByNameAndCity(
            @RequestParam String name, @RequestParam String city) {
        List<Event> events = eventService.getEventsByNameAndCity(name, city);
        return ResponseEntity.ok(events);
    }

    // Buscar eventos por nombre o ciudad
    @GetMapping("/find_by_name_or_city")
    public ResponseEntity<List<Event>> getEventsByNameOrCity(
            @RequestParam String name, @RequestParam String city) {
        List<Event> events = eventService.getEventsByNameOrCity(name, city);
        return ResponseEntity.ok(events);
    }

    // Buscar eventos por fecha y estado
    @GetMapping("/find_by_date_and_status")
    public ResponseEntity<List<Event>> getEventsByDateAndStatus(
            @RequestParam String date, @RequestParam EventStatus status) {
        List<Event> events = eventService.getEventsByDateAndStatus(date, status);
        return ResponseEntity.ok(events);
    }
}