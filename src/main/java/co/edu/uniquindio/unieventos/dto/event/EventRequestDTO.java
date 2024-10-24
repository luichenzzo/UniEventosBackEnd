package co.edu.uniquindio.unieventos.dto.event;

import co.edu.uniquindio.unieventos.dto.location.LocationRequestDTO;
import co.edu.uniquindio.unieventos.model.enums.EventStatus;
import co.edu.uniquindio.unieventos.model.enums.EventType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record EventRequestDTO(
        String code,
        String address,
        String city,
        String description,
        MultipartFile posterImage,  // Cambia a MultipartFile
        MultipartFile locationImage,  // Cambia a MultipartFile
        String name,
        String date,
        String dateTime,
        List<LocationRequestDTO> listLocations,
        EventType eventType,
        EventStatus eventStatus
) {}

