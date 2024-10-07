package co.edu.uniquindio.unieventos.dto.location;

public record LocationRequestDTO(
        String name,
        double price,
        int ticketsSold,
        int maxCapacity
) {}

