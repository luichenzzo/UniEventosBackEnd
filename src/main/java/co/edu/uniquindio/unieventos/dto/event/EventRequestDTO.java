package co.edu.uniquindio.unieventos.dto.event;

import co.edu.uniquindio.unieventos.dto.location.LocationRequestDTO;
import co.edu.uniquindio.unieventos.model.enums.EventStatus;
import co.edu.uniquindio.unieventos.model.enums.EventType;

import java.util.List;

public record EventRequestDTO(
        String code,
        String address,
        String city,
        String description,
        String posterImage,
        String locationImage,
        String name,
        String date,
        String dateTime,
        List<LocationRequestDTO> listLocations,
        EventType eventType,
        EventStatus eventStatus
) {}

