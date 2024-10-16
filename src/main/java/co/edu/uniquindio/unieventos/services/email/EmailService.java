package co.edu.uniquindio.unieventos.services.email;

import co.edu.uniquindio.unieventos.dto.email.EmailDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendEmailWithTemplate(EmailDTO emailDTO) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(emailDTO.getRecipient());
            helper.setSubject(emailDTO.getSubject());

            // Cargar la plantilla HTML usando Thymeleaf
            Context context = new Context();
            context.setVariable("msgBody", emailDTO.getMsgBody());
            String htmlContent = templateEngine.process("emailTemplate", context);
            helper.setText(htmlContent, true); // El segundo parámetro indica que es HTML

            // Enviar correo
            mailSender.send(message);
            System.out.println("Correo enviado con éxito a " + emailDTO.getRecipient());
        } catch (Exception e) {
            System.out.println("Error al enviar el correo: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
