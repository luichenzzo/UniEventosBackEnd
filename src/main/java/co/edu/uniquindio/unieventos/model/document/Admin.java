package co.edu.uniquindio.unieventos.model.document;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "admins")
@Builder
@ToString
public class Admin {
    @Id
    private String usuario;
    private String contrasenia;
}

