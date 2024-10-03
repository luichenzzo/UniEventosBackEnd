package com.example.unieventosbackend.model.documents;

import com.example.unieventosbackend.model.vo.ReceiptDetail;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Receipts")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Receipt {
    @Id
    private String code;
    private String date;
    private double total;
    // Añadir la lista de ReceiptDetail como parte del objeto Receipt
    private List<ReceiptDetail> receiptDetails;
}
