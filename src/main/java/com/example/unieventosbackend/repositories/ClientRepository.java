package com.example.unieventosbackend.repositories;


import com.example.unieventosbackend.model.documents.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {


    // Buscar por "firstName"
    @Query
    List<Client> findByFirstName(String firstName);

    // Buscar por "lastName"
    @Query
    List<Client> findByLastName(String lastName);

    // Buscar por "address"
    @Query
    List<Client> findByAddress(String address);

    // Buscar por "phoneNumber"
    @Query
    List<Client> findByPhoneNumber(String phoneNumber);

    // Buscar por "email"
    @Query
    List<Client> findByEmail(String email);

    // Buscar por "firstName" y "lastName"
    @Query
    List<Client> findByFirstNameAndLastName(String firstName, String lastName);

    @Query
    Client updateClient(String id, Client clientDetails);

    // Eliminar por "id"
    void deleteById(String id);

    // Eliminar por "email"
    void deleteByEmail(String email);
}
