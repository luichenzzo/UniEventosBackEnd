package co.edu.uniquindio.unieventos.dto.email;

// Importing required classes
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Annotations
@Data
@AllArgsConstructor
@NoArgsConstructor

// Class
public class EmailDTO {

        // Class data members
        private String recipient;
        private String msgBody;
        private String subject;
        private String attachment;
}
