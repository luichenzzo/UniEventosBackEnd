package com.example.unieventosbackend.dto;

public record InfoEventoDTO(
        String code,
        String address,
        String city,
        String description,
        String posterImage,
        String locationImage,
        String name,
        String date,
        String EventType
) {
}
