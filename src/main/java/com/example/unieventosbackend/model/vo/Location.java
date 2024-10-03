package com.example.unieventosbackend.model.vo;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Location {

    @EqualsAndHashCode.Include
    private String nombre;
    private int precio;
    private int capacidadMaxima;
    private int ticketSold;

    @Builder
    public Location(String nombre, int precio, int capacidadMaxima) {
        this.nombre = nombre;
        this.precio = precio;
        this.capacidadMaxima = capacidadMaxima;
        this.ticketSold = 0;
    }
}
