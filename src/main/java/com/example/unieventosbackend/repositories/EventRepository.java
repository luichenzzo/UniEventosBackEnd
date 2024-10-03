package com.example.unieventosbackend.repositories;


import com.example.unieventosbackend.model.documents.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {

    // Buscar por "code"
    @Query
    List<Event> findByCode(String code);

    // Buscar por "name"
    @Query
    List<Event> findByName(String name);

    // Buscar por "code" y "name"
    @Query
    List<Event> findByCodeAndName(String code, String name);
}
