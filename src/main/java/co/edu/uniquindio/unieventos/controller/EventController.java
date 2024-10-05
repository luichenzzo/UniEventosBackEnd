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

    @GetMapping("/getAll")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    // Obtener un evento por ID
    @GetMapping("/getById")
    public ResponseEntity<Optional<Event>> getEventById(@RequestParam String id) {
        Optional<Event> event = eventService.getEventById(id);
        return ResponseEntity.ok(event);
    }

    // Crear o actualizar un evento
    @PostMapping("/createOrSave")
    public ResponseEntity<Event> saveEvent(@RequestBody Event event) {
        Event savedEvent = eventService.saveOrUpdateEvent(event);
        return ResponseEntity.ok(savedEvent);
    }

    // Eliminar un evento por ID
    @DeleteMapping("/deleteById")
    public ResponseEntity<Void> deleteEvent(@RequestParam String id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok().build();
    }

    // Buscar eventos por nombre
    @GetMapping("/findByName")
    public ResponseEntity<List<Event>> getEventsByName(@RequestParam String name) {
        List<Event> events = eventService.getEventsByName(name);
        return ResponseEntity.ok(events);
    }

    // Buscar eventos por ciudad
    @GetMapping("/findByCity")
    public ResponseEntity<List<Event>> getEventsByCity(@RequestParam String city) {
        List<Event> events = eventService.getEventsByCity(city);
        return ResponseEntity.ok(events);
    }

    // Buscar eventos por fecha
    @GetMapping("/findByDate")
    public ResponseEntity<List<Event>> getEventsByDate(@RequestParam String date) {
        List<Event> events = eventService.getEventsByDate(date);
        return ResponseEntity.ok(events);
    }

    // Buscar eventos por estado
    @GetMapping("/findByStatus")
    public ResponseEntity<List<Event>> getEventsByStatus(@RequestParam EventStatus status) {
        List<Event> events = eventService.getEventsByStatus(status);
        return ResponseEntity.ok(events);
    }

    // Buscar eventos por nombre y ciudad
    @GetMapping("/findByNameAndCity")
    public ResponseEntity<List<Event>> getEventsByNameAndCity(@RequestParam String name, @RequestParam String city) {
        List<Event> events = eventService.getEventsByNameAndCity(name, city);
        return ResponseEntity.ok(events);
    }

    // Buscar eventos por nombre o ciudad
    @GetMapping("/findByNameOrCity")
    public ResponseEntity<List<Event>> getEventsByNameOrCity(@RequestParam String name, @RequestParam String city) {
        List<Event> events = eventService.getEventsByNameOrCity(name, city);
        return ResponseEntity.ok(events);
    }

    // Buscar eventos por fecha y estado
    @GetMapping("/findByDateAndStatus")
    public ResponseEntity<List<Event>> getEventsByDateAndStatus(@RequestParam String date, @RequestParam EventStatus status) {
        List<Event> events = eventService.getEventsByDateAndStatus(date, status);
        return ResponseEntity.ok(events);
    }
}