package co.edu.uniquindio.unieventos.dto;

import co.edu.uniquindio.unieventos.model.enums.EventStatus;
import co.edu.uniquindio.unieventos.model.enums.EventType;

public record RegisterEventDto(
        String code,
        String address,
        String city,
        String description,
        String posterImage,
        String locationImage,
        String name,
        String date,
        String dateTime,
        EventType eventType,
        EventStatus eventStatus
) {}
