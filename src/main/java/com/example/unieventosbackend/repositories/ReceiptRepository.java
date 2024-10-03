package com.example.unieventosbackend.repositories;

import com.example.unieventosbackend.model.documents.Receipt;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptRepository extends MongoRepository<Receipt, String> {
    // Buscar recibos por rango de fechas
    List<Receipt> findByDateBetween(String startDate, String endDate);

    // Buscar recibos donde el total es mayor que un valor dado
    List<Receipt> findByTotalGreaterThan(double amount);

    // Buscar recibos donde el total es menor que un valor dado
    List<Receipt> findByTotalLessThan(double amount);

    // Buscar recibos por año (parte de la cadena de la fecha)
    @Query("{ 'date' : { $regex: ?0 } }")
    List<Receipt> findByYear(String year);  // Ejemplo de uso: findByYear("2023")

    // Buscar recibos por mes (parte de la cadena de la fecha)
    @Query("{ 'date' : { $regex: ?0 } }")
    List<Receipt> findByMonth(String month); // Ejemplo de uso: findByMonth("2023-09")

    // Buscar recibos por total en un rango específico
    List<Receipt> findByTotalBetween(double minTotal, double maxTotal);

    // Buscar recibos cuyo código contenga una cadena específica
    @Query("{ 'code' : { $regex: ?0, $options: 'i' } }")
    List<Receipt> findByCodeContaining(String partOfCode);

    // Buscar recibos por una fecha específica
    List<Receipt> findByDate(String date);

    // Eliminar recibos por una fecha específica
    void deleteByDate(String date);

    // Eliminar recibos en un rango de fechas
    void deleteByDateBetween(String startDate, String endDate);

    // Buscar recibos con un total exacto
    List<Receipt> findByTotal(double total);

    // Contar recibos con un total mayor que un valor específico
    long countByTotalGreaterThan(double total);

    // Buscar recibos que contengan un detalle con un código específico
    @Query("{ 'receiptDetails.code': ?0 }")
    List<Receipt> findByReceiptDetailCode(String code);

    // Buscar recibos que contengan un detalle con una descripción específica
    @Query("{ 'receiptDetails.description': ?0 }")
    List<Receipt> findByReceiptDetailDescription(String description);

    // Buscar recibos que contengan un detalle con una cantidad específica
    @Query("{ 'receiptDetails.quantity': ?0 }")
    List<Receipt> findByReceiptDetailQuantity(int quantity);

    // Buscar recibos que contengan un detalle con un subTotal mayor o igual a un valor dado
    @Query("{ 'receiptDetails.subTotal': { $gte: ?0 } }")
    List<Receipt> findByReceiptDetailSubTotalGreaterThanEqual(double subTotal);
}

