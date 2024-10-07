package co.edu.uniquindio.unieventos.dto.jwt;

import jakarta.validation.constraints.NotBlank;

public record TokenDTO(
        @NotBlank(message = "Token cannot be empty")
        String token
) {
}
