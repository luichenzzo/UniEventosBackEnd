package co.edu.uniquindio.unieventos.dto.receipt;

import co.edu.uniquindio.unieventos.model.vo.ReceiptDetail;

import java.util.Date;
import java.util.List;

public record ReceiptResponseDTO(
        String code,
        Date date,
        double total,
        List<ReceiptDetail> listReceiptDetails
) {}

