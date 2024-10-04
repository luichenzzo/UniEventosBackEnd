package co.edu.uniquindio.unieventos.repositories;

import co.edu.uniquindio.unieventos.model.document.Event;
import co.edu.uniquindio.unieventos.model.enums.EventStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {

    // Buscar eventos por nombre
    @Query
    List<Event> findByNameContainsIgnoreCase(String name);

    // Buscar eventos por ciudad
    @Query
    List<Event> findByCityContainsIgnoreCase(String city);

    // Buscar eventos por fecha
    @Query
    List<Event> findByDateContainingIgnoreCase(String date);

    // Buscar eventos por estado (EventStatus)
    @Query
    List<Event> findByEventStatusIgnoreCase(EventStatus eventStatus);

    // Búsqueda combinada usando AND
    List<Event> findByNameContainingIgnoreCaseAndCityContainingIgnoreCase(String name, String city);

    // Búsqueda combinada usando OR
    List<Event> findByNameContainingIgnoreCaseOrCityContainingIgnoreCase(String name, String city);

    // Búsqueda combinada con fecha y estado
    List<Event> findByDateIgnoreCaseAndEventStatusIgnoreCase(String date, EventStatus eventStatus);
}


