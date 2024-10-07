package co.edu.uniquindio.unieventos.dto.jwt;

import jakarta.validation.constraints.NotBlank;

public record ValidationDTO(
        @NotBlank(message = "Field cannot be empty")
        String field,

        @NotBlank(message = "Message cannot be empty")
        String message
) {
}
