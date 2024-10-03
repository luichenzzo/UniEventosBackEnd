package com.example.unieventosbackend.controllers;


import com.example.unieventosbackend.model.documents.Client;
import com.example.unieventosbackend.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // Crear un cliente nuevo
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client savedClient = clientService.createClient(client);
        return ResponseEntity.ok(savedClient);
    }

    // Obtener todos los clientes
    @GetMapping("/all")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }


}

