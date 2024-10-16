package co.edu.uniquindio.unieventos.services.email;


// Importing required classes
import co.edu.uniquindio.unieventos.dto.email.EmailDTO;

// Interface
public interface IEmailService {

    // Method
    // To send a simple email
    String sendSimpleMail(EmailDTO details);

    // Method
    // To send an email with attachment
    String sendMailWithAttachment(EmailDTO details);
}
