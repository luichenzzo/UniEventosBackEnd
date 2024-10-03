package com.example.unieventosbackend.model.vo;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ReceiptDetail {
    private String code;
    private String description;
    private int quantity;
    private double subTotal;
}
