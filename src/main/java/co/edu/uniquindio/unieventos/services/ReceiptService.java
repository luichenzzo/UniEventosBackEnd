package co.edu.uniquindio.unieventos.services;

import co.edu.uniquindio.unieventos.dto.receipt.ReceiptRequestDTO;
import co.edu.uniquindio.unieventos.dto.receipt.ReceiptResponseDTO;
import co.edu.uniquindio.unieventos.model.document.Receipt;
import co.edu.uniquindio.unieventos.repositories.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    private Receipt convertToEntity(ReceiptRequestDTO dto) {
        return Receipt.builder()
                .code(dto.code())
                .date(dto.date())
                .total(dto.total())
                .listReceiptDetails(dto.listReceiptDetails())
                .build();
    }

    private ReceiptResponseDTO convertToDTO(Receipt receipt) {
        return new ReceiptResponseDTO(
                receipt.getCode(),
                receipt.getDate(),
                receipt.getTotal(),
                receipt.getListReceiptDetails()
        );
    }

    public ReceiptResponseDTO createReceipt(ReceiptRequestDTO requestDTO) {
        Receipt receipt = convertToEntity(requestDTO);
        receipt = receiptRepository.save(receipt); // Guardar el recibo en la base de datos
        return convertToDTO(receipt);
    }

    public List<ReceiptResponseDTO> getAllReceipts() {
        return receiptRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Método para buscar por código
    public List<ReceiptResponseDTO> getReceiptsByCode(String code) {
        return receiptRepository.findByCode(code).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Método para buscar por fecha
    public List<ReceiptResponseDTO> getReceiptsByDate(Date date) {
        return receiptRepository.findByDate(date).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Método para buscar por rango de fechas
    public List<ReceiptResponseDTO> getReceiptsByDateRange(Date startDate, Date endDate) {
        return receiptRepository.findByDateBetween(startDate, endDate).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
