package co.edu.uniquindio.unieventos.services.email;

import co.edu.uniquindio.unieventos.dto.email.EmailDTO;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService implements IEmailService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public EmailService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendMail(EmailDTO correoRequest) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(correoRequest.getAddressee());
            helper.setSubject(correoRequest.getSubject());

            // Procesar la plantilla Thymeleaf
            Context context = new Context();
            context.setVariable("mensaje", correoRequest.getMessage());
            String contenidoHtml = templateEngine.process("email", context);

            helper.setText(contenidoHtml, true);

            javaMailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Error al enviar el correo: " + e.getMessage(), e);
        }
    }
}