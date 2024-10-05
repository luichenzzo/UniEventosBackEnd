package co.edu.uniquindio.unieventos.controller;

import co.edu.uniquindio.unieventos.dto.receipt.ReceiptRequestDTO;
import co.edu.uniquindio.unieventos.dto.receipt.ReceiptResponseDTO;
import co.edu.uniquindio.unieventos.services.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receipts")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @PostMapping("/create")
    public ResponseEntity<ReceiptResponseDTO> createReceipt(@RequestBody ReceiptRequestDTO receiptRequestDTO) {
        ReceiptResponseDTO receipt = receiptService.createReceipt(receiptRequestDTO);
        return ResponseEntity.ok(receipt); // ResponseEntity con .ok
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<ReceiptResponseDTO>> getAllReceipts() {
        List<ReceiptResponseDTO> receipts = receiptService.getAllReceipts();
        return ResponseEntity.ok(receipts);
    }
}

