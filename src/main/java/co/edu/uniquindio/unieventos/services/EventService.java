package co.edu.uniquindio.unieventos.services;

import co.edu.uniquindio.unieventos.dto.event.EventRequestDTO;
import co.edu.uniquindio.unieventos.dto.event.EventResponseDTO;
import co.edu.uniquindio.unieventos.dto.location.LocationResponseDTO;
import co.edu.uniquindio.unieventos.model.document.Event;
import co.edu.uniquindio.unieventos.model.enums.EventStatus;
import co.edu.uniquindio.unieventos.model.vo.Location;
import co.edu.uniquindio.unieventos.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ImageUploadService imageUploadService;

    // Método para convertir de DTO a entidad
    private Event convertToEntity(EventRequestDTO dto) {
        List<Location> locations = dto.listLocations().stream()
                .map(locationDTO -> new Location(
                        locationDTO.name(),
                        locationDTO.price(),
                        locationDTO.ticketsSold(),
                        locationDTO.maxCapacity()
                )).collect(Collectors.toList());

        return Event.builder()
                .code(dto.code())
                .address(dto.address())
                .city(dto.city())
                .description(dto.description())
                .posterImage(dto.posterImage())
                .locationImage(dto.locationImage())
                .name(dto.name())
                .date(dto.date())
                .dateTime(dto.dateTime())
                .listLocations(locations)
                .eventType(dto.eventType())
                .eventStatus(dto.eventStatus())
                .build();
    }

    // Método para convertir de entidad a DTO
    private EventResponseDTO convertToDTO(Event event) {
        List<LocationResponseDTO> locations = event.getListLocations().stream()
                .map(location -> new LocationResponseDTO(
                        location.getName(),
                        location.getPrice(),
                        location.getTicketsSold(),
                        location.getMaxCapacity(),
                        location.isCapacityAvaible(0) // Verifica si hay capacidad disponible
                )).collect(Collectors.toList());

        return new EventResponseDTO(
                event.getCode(),
                event.getAddress(),
                event.getCity(),
                event.getDescription(),
                event.getPosterImage(),
                event.getLocationImage(),
                event.getName(),
                event.getDate(),
                event.getDateTime(),
                locations,
                event.getEventType(),
                event.getEventStatus()
        );
    }

    // Obtener todos los eventos
    public List<EventResponseDTO> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Obtener evento por ID
    public Optional<EventResponseDTO> getEventById(String id) {
        return eventRepository.findById(id)
                .map(this::convertToDTO);
    }

    // Guardar o actualizar un evento
    public EventResponseDTO saveOrUpdateEvent(EventRequestDTO eventRequestDTO) {
        Event event = convertToEntity(eventRequestDTO);
        event = eventRepository.save(event);
        return convertToDTO(event);
    }

    // Eliminar un evento por ID
    public void deleteEvent(String id) {
        eventRepository.deleteById(id);
    }

    // Buscar eventos por nombre
    public List<EventResponseDTO> getEventsByName(String name) {
        return eventRepository.findByNameContainsIgnoreCase(name).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Buscar eventos por ciudad
    public List<EventResponseDTO> getEventsByCity(String city) {
        return eventRepository.findByCityContainsIgnoreCase(city).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Buscar eventos por fecha
    public List<EventResponseDTO> getEventsByDate(String date) {
        return eventRepository.findByDateContainingIgnoreCase(date).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Buscar eventos por estado
    public List<EventResponseDTO> getEventsByStatus(EventStatus status) {
        return eventRepository.findByEventStatusIgnoreCase(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Buscar eventos combinados por nombre y ciudad
    public List<EventResponseDTO> getEventsByNameAndCity(String name, String city) {
        return eventRepository.findByNameContainingIgnoreCaseAndCityContainingIgnoreCase(name, city).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Buscar eventos combinados por nombre o ciudad
    public List<EventResponseDTO> getEventsByNameOrCity(String name, String city) {
        return eventRepository.findByNameContainingIgnoreCaseOrCityContainingIgnoreCase(name, city).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Buscar eventos combinados por fecha y estado
    public List<EventResponseDTO> getEventsByDateAndStatus(String date, EventStatus status) {
        return eventRepository.findByDateIgnoreCaseAndEventStatusIgnoreCase(date, status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EventResponseDTO createEvent(EventRequestDTO eventRequestDTO) {
        Event event = convertToEntity(eventRequestDTO);
        event = eventRepository.save(event);
        return convertToDTO(event);
    }
}


