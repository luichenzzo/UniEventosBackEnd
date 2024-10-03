package com.example.unieventosbackend.model.documents;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "coupon")
public class Coupon {
    @Id
    private String codigo;
    private String nombre;
    private double porcentajeDescuento;
    private String fechaVencimiento;
}

