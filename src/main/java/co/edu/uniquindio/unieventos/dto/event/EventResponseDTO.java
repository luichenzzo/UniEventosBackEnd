package co.edu.uniquindio.unieventos.dto.event;

import co.edu.uniquindio.unieventos.dto.location.LocationResponseDTO;
import co.edu.uniquindio.unieventos.model.enums.EventStatus;
import co.edu.uniquindio.unieventos.model.enums.EventType;

import java.util.Date;

import java.util.List;

public record EventResponseDTO(
        String code,
        String address,
        String city,
        String description,
        String posterImage,
        String locationImage,
        String name,
        String date,
        String dateTime,
        List<LocationResponseDTO> listLocations, // Aquí se usa el DTO de Location
        EventType eventType,
        EventStatus eventStatus
) {}

