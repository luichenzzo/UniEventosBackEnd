package co.edu.uniquindio.unieventos.dto.email;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmailDTO {
        private String addressee;
        private String subject;
        private String message;
}
