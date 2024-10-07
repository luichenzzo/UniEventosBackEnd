package co.edu.uniquindio.unieventos.dto.location;

public record LocationResponseDTO(
        String name,
        double price,
        int ticketsSold,
        int maxCapacity,
        boolean capacityAvailable // Para indicar si hay capacidad disponible
) {}
