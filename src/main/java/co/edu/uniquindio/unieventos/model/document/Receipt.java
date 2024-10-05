package co.edu.uniquindio.unieventos.model.document;


import co.edu.uniquindio.unieventos.model.vo.ReceiptDetail;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "receipts")
@Builder
@ToString
public class Receipt {
    @Id
    private String code;
    private Date date;
    private double total;
    private List<ReceiptDetail> listReceiptDetails;
}
