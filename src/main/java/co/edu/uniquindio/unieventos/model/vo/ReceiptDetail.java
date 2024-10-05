package co.edu.uniquindio.unieventos.model.vo;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class ReceiptDetail {

    @EqualsAndHashCode.Include
    private String code;
    private String description;
    private int quantity;
    private double subTotal;

    @Builder

    public ReceiptDetail(String code, String description, int quantity, double subTotal) {
        this.code = code;
        this.description = description;
        this.quantity = quantity;
        this.subTotal = subTotal;
    }
}
