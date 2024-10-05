package co.edu.uniquindio.unieventos.repositories;

import co.edu.uniquindio.unieventos.model.document.Receipt;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReceiptRepository extends MongoRepository<Receipt, String> {

    // Buscar recibos por código
    List<Receipt> findByCode(String code);

    // Buscar recibos por fecha exacta
    List<Receipt> findByDate(Date date);

    // Buscar recibos dentro de un rango de fechas
    List<Receipt> findByDateBetween(Date startDate, Date endDate);
}

