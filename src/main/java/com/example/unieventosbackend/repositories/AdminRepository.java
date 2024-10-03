package com.example.unieventosbackend.repositories;


import com.example.unieventosbackend.model.documents.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {

    // Buscar por "usuario"
    @Query
    List<Admin> findByUsuario(String usuario);

    // Buscar por "contrasenia"
    @Query
    List<Admin> findByContrasenia(String contrasenia);

    // Actualizar "usuario" por "id"
    @Query("{ '_id': ?0 } { '$set': { 'usuario': ?1 } }")
    void updateUsuarioById(String id, String usuario);

    // Actualizar "contrasenia" por "id"
    @Query("{ '_id': ?0 } { '$set': { 'contrasenia': ?1 } }")
    void updateContraseniaById(String id, String contrasenia);

    // Eliminar por "id"
    void deleteById(String id);

    // Eliminar por "usuario"
    void deleteByUsuario(String usuario);
}
