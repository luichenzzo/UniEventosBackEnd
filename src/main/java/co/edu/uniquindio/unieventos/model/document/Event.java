package co.edu.uniquindio.unieventos.model.document;

import co.edu.uniquindio.unieventos.model.enums.EventStatus;
import co.edu.uniquindio.unieventos.model.enums.EventType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "events")
@Builder
@ToString
public class Event {

    @Id
    private String code;
    private String address;
    private String city;
    private String description;
    private String posterImage;
    private String locationImage;
    private String name;
    private String date;
    private String dateTime;
    private EventType eventType;
    private EventStatus eventStatus;
}
