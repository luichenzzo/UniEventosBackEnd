package co.edu.uniquindio.unieventos.services.email;

import co.edu.uniquindio.unieventos.dto.email.EmailDTO;
import jakarta.mail.MessagingException;

public interface IEmailService {
    void sendMail(EmailDTO emailDTO) throws MessagingException;
}
