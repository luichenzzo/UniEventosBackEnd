package com.example.unieventosbackend.controllers;

import com.example.unieventosbackend.model.documents.Event;
import com.example.unieventosbackend.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    // Create a new event
    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event createdEvent = eventService.saveEvent(event);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    // Get all events
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    // Get an event by code
    @GetMapping("/code/{code}")
    public ResponseEntity<List<Event>> getEventByCode(@PathVariable String code) {
        List<Event> events = eventService.buscarPorCode(code);
        if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    // Get an event by name
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Event>> getEventByName(@PathVariable String name) {
        List<Event> events = eventService.buscarPorName(name);
        if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    // Get an event by both code and name
    @GetMapping("/search")
    public ResponseEntity<List<Event>> getEventByCodeAndName(@RequestParam String code, @RequestParam String name) {
        List<Event> events = eventService.buscarPorCodeYName(code, name);
        if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    // Update an event
    @PutMapping("/{code}")
    public ResponseEntity<Event> updateEvent(@PathVariable String code, @RequestBody Event eventDetails) {
        Event updatedEvent = eventService.updateEvent(code, eventDetails);
        if (updatedEvent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }

    // Delete an event
    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteEvent(@PathVariable String code) {
        boolean deleted = eventService.deleteEvent(code);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
