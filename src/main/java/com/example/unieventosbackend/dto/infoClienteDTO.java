package com.example.unieventosbackend.dto;

public record infoClienteDTO(
        String id,
        String firstName,
        String lastName,
        String address,
        String phoneNumber,
        String email,
        String password
) {
}
