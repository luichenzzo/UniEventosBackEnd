package com.example.unieventosbackend.controllers;


import com.example.unieventosbackend.model.documents.Receipt;
import com.example.unieventosbackend.services.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    // Guardar o actualizar un recibo
    @PostMapping
    public Receipt saveReceipt(@RequestBody Receipt receipt) {
        return receiptService.saveReceipt(receipt);
    }

    // Obtener todos los recibos
    @GetMapping
    public List<Receipt> getAllReceipts() {
        return receiptService.getAllReceipts();
    }

    // Obtener un recibo por ID
    @GetMapping("/{id}")
    public Optional<Receipt> getReceiptById(@PathVariable String id) {
        return receiptService.getReceiptById(id);
    }

    // Obtener recibos por rango de fechas
    @GetMapping("/dates")
    public List<Receipt> getReceiptsByDateRange(@RequestParam String startDate, @RequestParam String endDate) {
        return receiptService.getReceiptsByDateRange(startDate, endDate);
    }

    // Obtener recibos cuyo total es mayor que un valor dado
    @GetMapping("/total/greaterThan/{total}")
    public List<Receipt> getReceiptsWithTotalGreaterThan(@PathVariable double total) {
        return receiptService.getReceiptsWithTotalGreaterThan(total);
    }

    // Obtener recibos cuyo total es menor que un valor dado
    @GetMapping("/total/lessThan/{total}")
    public List<Receipt> getReceiptsWithTotalLessThan(@PathVariable double total) {
        return receiptService.getReceiptsWithTotalLessThan(total);
    }

    // Obtener recibos por código que contiene una parte específica
    @GetMapping("/code/{partOfCode}")
    public List<Receipt> getReceiptsByCodeContaining(@PathVariable String partOfCode) {
        return receiptService.getReceiptsByCodeContaining(partOfCode);
    }

    // Obtener recibos cuyo total sea exacto
    @GetMapping("/total/exact/{total}")
    public List<Receipt> getReceiptsByTotal(@PathVariable double total) {
        return receiptService.getReceiptsByTotal(total);
    }

    // Obtener recibos cuyo total esté en un rango
    @GetMapping("/total/range")
    public List<Receipt> getReceiptsByTotalBetween(@RequestParam double minTotal, @RequestParam double maxTotal) {
        return receiptService.getReceiptsByTotalBetween(minTotal, maxTotal);
    }

    // Obtener recibos por año
    @GetMapping("/year/{year}")
    public List<Receipt> getReceiptsByYear(@PathVariable String year) {
        return receiptService.getReceiptsByYear(year);
    }

    // Obtener recibos por mes
    @GetMapping("/month/{month}")
    public List<Receipt> getReceiptsByMonth(@PathVariable String month) {
        return receiptService.getReceiptsByMonth(month);
    }

    // Eliminar un recibo por ID
    @DeleteMapping("/{id}")
    public void deleteReceiptById(@PathVariable String id) {
        receiptService.deleteReceiptById(id);
    }

    // Eliminar recibos por fecha específica
    @DeleteMapping("/date/{date}")
    public void deleteReceiptsByDate(@PathVariable String date) {
        receiptService.deleteReceiptsByDate(date);
    }

    // Eliminar recibos por rango de fechas
    @DeleteMapping("/dates")
    public void deleteReceiptsByDateRange(@RequestParam String startDate, @RequestParam String endDate) {
        receiptService.deleteReceiptsByDateRange(startDate, endDate);
    }

    // Contar recibos cuyo total sea mayor que un valor dado
    @GetMapping("/count/totalGreaterThan/{total}")
    public long countReceiptsWithTotalGreaterThan(@PathVariable double total) {
        return receiptService.countReceiptsWithTotalGreaterThan(total);
    }

    // Buscar recibos por detalle con un código específico
    @GetMapping("/detail/code/{code}")
    public List<Receipt> getReceiptsByReceiptDetailCode(@PathVariable String code) {
        return receiptService.getReceiptsByReceiptDetailCode(code);
    }

    // Buscar recibos por detalle con una descripción específica
    @GetMapping("/detail/description/{description}")
    public List<Receipt> getReceiptsByReceiptDetailDescription(@PathVariable String description) {
        return receiptService.getReceiptsByReceiptDetailDescription(description);
    }

    // Buscar recibos por detalle con una cantidad específica
    @GetMapping("/detail/quantity/{quantity}")
    public List<Receipt> getReceiptsByReceiptDetailQuantity(@PathVariable int quantity) {
        return receiptService.getReceiptsByReceiptDetailQuantity(quantity);
    }

    // Buscar recibos cuyo subTotal de detalle sea mayor o igual a un valor dado
    @GetMapping("/detail/subtotal/{subTotal}")
    public List<Receipt> getReceiptsByReceiptDetailSubTotalGreaterThanEqual(@PathVariable double subTotal) {
        return receiptService.getReceiptsByReceiptDetailSubTotalGreaterThanEqual(subTotal);
    }

}

