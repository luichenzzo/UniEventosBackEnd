package co.edu.uniquindio.unieventos.repositories;

import co.edu.uniquindio.unieventos.model.document.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {

    @Query
    List<Admin> findByUsuario(String usuario);

    // Buscar por "contrasenia"

    // Eliminar por "id"
    void deleteById(String id);

    // Eliminar por "usuario"
    void deleteByUsuario(String usuario);

    @Query
    Optional<Admin> findByUsuarioAndContrasenia(String usuario, String contrasenia);

}
