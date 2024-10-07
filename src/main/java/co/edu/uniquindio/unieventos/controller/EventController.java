package co.edu.uniquindio.unieventos.controller;

import co.edu.uniquindio.unieventos.dto.event.EventRequestDTO;
import co.edu.uniquindio.unieventos.dto.event.EventResponseDTO;
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
    public ResponseEntity<List<EventResponseDTO>> getAllEvents() {
        List<EventResponseDTO> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<EventResponseDTO> getEventById(@PathVariable String id) {
        Optional<EventResponseDTO> event = eventService.getEventById(id);
        return event.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<EventResponseDTO> createEvent(@RequestBody EventRequestDTO eventRequestDTO) {
        EventResponseDTO event = eventService.saveOrUpdateEvent(eventRequestDTO);
        return ResponseEntity.ok(event);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EventResponseDTO> updateEvent(@PathVariable String id, @RequestBody EventRequestDTO eventRequestDTO) {
        if (!eventService.getEventById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        EventResponseDTO updatedEvent = eventService.saveOrUpdateEvent(eventRequestDTO);
        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable String id) {
        if (!eventService.getEventById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        eventService.deleteEvent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search/name")
    public ResponseEntity<List<EventResponseDTO>> getEventsByName(@RequestParam String name) {
        List<EventResponseDTO> events = eventService.getEventsByName(name);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/search/city")
    public ResponseEntity<List<EventResponseDTO>> getEventsByCity(@RequestParam String city) {
        List<EventResponseDTO> events = eventService.getEventsByCity(city);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/search/date")
    public ResponseEntity<List<EventResponseDTO>> getEventsByDate(@RequestParam String date) {
        List<EventResponseDTO> events = eventService.getEventsByDate(date);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/search/status")
    public ResponseEntity<List<EventResponseDTO>> getEventsByStatus(@RequestParam EventStatus status) {
        List<EventResponseDTO> events = eventService.getEventsByStatus(status);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/search/name-and-city")
    public ResponseEntity<List<EventResponseDTO>> getEventsByNameAndCity(@RequestParam String name, @RequestParam String city) {
        List<EventResponseDTO> events = eventService.getEventsByNameAndCity(name, city);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/search/name-or-city")
    public ResponseEntity<List<EventResponseDTO>> getEventsByNameOrCity(@RequestParam String name, @RequestParam String city) {
        List<EventResponseDTO> events = eventService.getEventsByNameOrCity(name, city);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/search/date-and-status")
    public ResponseEntity<List<EventResponseDTO>> getEventsByDateAndStatus(@RequestParam String date, @RequestParam EventStatus status) {
        List<EventResponseDTO> events = eventService.getEventsByDateAndStatus(date, status);
        return ResponseEntity.ok(events);
    }
}