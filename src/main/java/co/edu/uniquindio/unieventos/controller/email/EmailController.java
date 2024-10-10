package co.edu.uniquindio.unieventos.controller.email;

import co.edu.uniquindio.unieventos.dto.email.EmailDTO;
import co.edu.uniquindio.unieventos.services.email.EmailService;
import co.edu.uniquindio.unieventos.services.email.IEmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class EmailController {

    @Autowired
    IEmailService emailService;

    @PostMapping("/send-email")
    private ResponseEntity<String> sendEmail(@RequestBody EmailDTO email) {
        try {
            emailService.sendMail(email);
            return new ResponseEntity<>("Correo enviado exitosamente.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al enviar el correo: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
