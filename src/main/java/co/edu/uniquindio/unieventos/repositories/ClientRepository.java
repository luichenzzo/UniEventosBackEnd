package co.edu.uniquindio.unieventos.repositories;

import co.edu.uniquindio.unieventos.dto.client.ClientResponseDTO;
import co.edu.uniquindio.unieventos.model.document.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {

    @Query
    List<Client> findByFirstNameContainingIgnoreCase(String usuario);

    @Query
    List<Client> findByLastNameContainingIgnoreCase(String usuario);

    @Query
    List<Client> findByEmailContainingIgnoreCase(String usuario);

    @Query
    List<Client> findByPhoneNumberContaining(String usuario);

    @Query
    Optional<Client> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(String firstName, String lastName);

    @Query
    List<Client> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);


    @Query
    Optional<ClientResponseDTO> getClientById(String id);

    @Query
    ClientResponseDTO findByEmail(String email);

    @Query
    ClientResponseDTO findByEmailAndPassword(String email, String password);
}
