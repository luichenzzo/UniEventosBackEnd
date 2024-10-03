package com.example.unieventosbackend.model.documents;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "admins")
public class Admin {
    @Id
    private String usuario;
    private String contrasenia;
}

