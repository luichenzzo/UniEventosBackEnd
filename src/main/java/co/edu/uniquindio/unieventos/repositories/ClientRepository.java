package co.edu.uniquindio.unieventos.repositories;

import co.edu.uniquindio.unieventos.model.document.Admin;
import co.edu.uniquindio.unieventos.model.document.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {

    @Query
    List<Client> findByFirstName(String usuario);

    @Query
    List<Client> findByLastName(String usuario);

    @Query
    List<Client> findByEmail(String usuario);

    @Query
    List<Client> findByPhoneNumber(String usuario);


    @Query
    Optional<Client> findByFirstNameAndLastName(String firstName, String lastName);
}
