package co.edu.uniquindio.unieventos.dto.client;

public record ClientResponseDTO(
        String id,
        String firstName,
        String lastName,
        String address,
        String phoneNumber,
        String email,
        String password
) {
}
