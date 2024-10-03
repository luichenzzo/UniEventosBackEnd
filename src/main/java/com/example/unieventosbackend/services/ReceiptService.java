package com.example.unieventosbackend.services;


import com.example.unieventosbackend.model.documents.Receipt;
import com.example.unieventosbackend.repositories.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    // Guardar o actualizar un recibo
    public Receipt saveReceipt(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    // Obtener todos los recibos
    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    // Obtener un recibo por su código
    public Optional<Receipt> getReceiptById(String id) {
        return receiptRepository.findById(id);
    }

    // Buscar recibos por rango de fechas
    public List<Receipt> getReceiptsByDateRange(String startDate, String endDate) {
        return receiptRepository.findByDateBetween(startDate, endDate);
    }

    // Buscar recibos cuyo total sea mayor que un valor dado
    public List<Receipt> getReceiptsWithTotalGreaterThan(double total) {
        return receiptRepository.findByTotalGreaterThan(total);
    }

    // Buscar recibos cuyo total sea menor que un valor dado
    public List<Receipt> getReceiptsWithTotalLessThan(double total) {
        return receiptRepository.findByTotalLessThan(total);
    }

    // Buscar recibos cuyo código contiene una parte específica de una cadena
    public List<Receipt> getReceiptsByCodeContaining(String partOfCode) {
        return receiptRepository.findByCodeContaining(partOfCode);
    }

    // Buscar recibos por total exacto
    public List<Receipt> getReceiptsByTotal(double total) {
        return receiptRepository.findByTotal(total);
    }

    // Buscar recibos cuyo total esté en un rango
    public List<Receipt> getReceiptsByTotalBetween(double minTotal, double maxTotal) {
        return receiptRepository.findByTotalBetween(minTotal, maxTotal);
    }

    // Buscar recibos por año
    public List<Receipt> getReceiptsByYear(String year) {
        return receiptRepository.findByYear(year);
    }

    // Buscar recibos por mes
    public List<Receipt> getReceiptsByMonth(String month) {
        return receiptRepository.findByMonth(month);
    }

    // Eliminar recibo por su ID
    public void deleteReceiptById(String id) {
        receiptRepository.deleteById(id);
    }

    // Eliminar recibos por fecha
    public void deleteReceiptsByDate(String date) {
        receiptRepository.deleteByDate(date);
    }

    // Eliminar recibos por rango de fechas
    public void deleteReceiptsByDateRange(String startDate, String endDate) {
        receiptRepository.deleteByDateBetween(startDate, endDate);
    }

    // Contar recibos cuyo total sea mayor que un valor dado
    public long countReceiptsWithTotalGreaterThan(double total) {
        return receiptRepository.countByTotalGreaterThan(total);
    }

    // Buscar recibos que tengan un detalle con un código específico
    public List<Receipt> getReceiptsByReceiptDetailCode(String code) {
        return receiptRepository.findByReceiptDetailCode(code);
    }

    // Buscar recibos que tengan un detalle con una descripción específica
    public List<Receipt> getReceiptsByReceiptDetailDescription(String description) {
        return receiptRepository.findByReceiptDetailDescription(description);
    }

    // Buscar recibos que tengan un detalle con una cantidad específica
    public List<Receipt> getReceiptsByReceiptDetailQuantity(int quantity) {
        return receiptRepository.findByReceiptDetailQuantity(quantity);
    }

    // Buscar recibos cuyo subTotal de un detalle sea mayor o igual a un valor dado
    public List<Receipt> getReceiptsByReceiptDetailSubTotalGreaterThanEqual(double subTotal) {
        return receiptRepository.findByReceiptDetailSubTotalGreaterThanEqual(subTotal);
    }
}

