package co.edu.uniquindio.unieventos.dto.client;

public record ClientRequestDTO(
         String id,
         String firstName,
         String lastName,
         String address,
         String phoneNumber,
         String email,
         String password
) {
}
