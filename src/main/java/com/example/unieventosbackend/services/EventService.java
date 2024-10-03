package com.example.unieventosbackend.services;


import com.example.unieventosbackend.model.documents.Event;
import com.example.unieventosbackend.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> buscarPorCode(String code) {
        return eventRepository.findByCode(code);
    }

    public List<Event> buscarPorName(String name) {
        return eventRepository.findByName(name);
    }

    public List<Event> buscarPorCodeYName(String code, String name) {
        return eventRepository.findByCodeAndName(code, name);
    }

    // Save a new event
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    // Get all events
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Update an existing event
    public Event updateEvent(String code, Event eventDetails) {
        return eventRepository.findById(code).map(event -> {
            event.setName(eventDetails.getName());
            event.setAddress(eventDetails.getAddress());
            event.setCity(eventDetails.getCity());
            event.setDate(eventDetails.getDate());
            event.setDescription(eventDetails.getDescription());
            event.setPosterImage(eventDetails.getPosterImage());
            event.setLocationImage(eventDetails.getLocationImage());
            event.setEventType(eventDetails.getEventType());
            event.setEventStatus(eventDetails.getEventStatus());
            return eventRepository.save(event);
        }).orElse(null);
    }

    // Delete an event
    public boolean deleteEvent(String code) {
        return eventRepository.findById(code).map(event -> {
            eventRepository.delete(event);
            return true;
        }).orElse(false);
    }
}
