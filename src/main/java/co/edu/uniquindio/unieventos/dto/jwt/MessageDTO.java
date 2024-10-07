package co.edu.uniquindio.unieventos.dto.jwt;

import jakarta.validation.constraints.NotNull;

public record MessageDTO<T>(
        @NotNull(message = "Error flag cannot be null")
        boolean error,

        T reply
) {
}
