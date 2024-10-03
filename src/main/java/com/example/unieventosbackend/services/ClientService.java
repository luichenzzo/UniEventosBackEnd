package com.example.unieventosbackend.services;


import com.example.unieventosbackend.model.documents.Client;
import com.example.unieventosbackend.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    // Crear un nuevo cliente
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    // Obtener todos los clientes
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public List<Client> findById(String code)
    {
        return clientRepository.findById(code).stream().toList();
    }

    List<Client> findByFirstName(String firstName)
    {
        return clientRepository.findByFirstName(firstName);
    }

    List<Client> findByLastName(String lastName)
    {
        return clientRepository.findByLastName(lastName);
    }

    List<Client> findByAddress(String address)
    {
        return clientRepository.findByAddress(address);
    }

    List<Client> findByPhoneNumber(String phoneNumber)
    {
        return clientRepository.findByPhoneNumber(phoneNumber);
    }

    List<Client> findByEmail(String email)
    {
        return clientRepository.findByEmail(email);
    }

    List<Client> findByFirstNameAndLastName(String firstName, String lastName)
    {
        return clientRepository.findByFirstNameAndLastName(firstName,lastName);
    }

    public Client updateClient(String id, Client clientDetails)
    {
        return clientRepository.findById(id).map(client -> {
            client.setFirstName(clientDetails.getFirstName());
            client.setLastName(clientDetails.getLastName());
            client.setAddress(clientDetails.getAddress());
            client.setPhoneNumber(clientDetails.getPhoneNumber());
            client.setPassword(clientDetails.getPassword());
            client.setEmail(clientDetails.getEmail());
            return clientRepository.save(client);
        }).orElse(null);
    }

    public boolean deleteById(String id)
    {
        return clientRepository.findById(id).map(client -> {
            clientRepository.delete(client);
            return true;
        }).orElse(false);
    }

}


