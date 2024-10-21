package co.edu.uniquindio.unieventos.model.document;

import co.edu.uniquindio.unieventos.model.enums.EventStatus;
import co.edu.uniquindio.unieventos.model.enums.EventType;
import co.edu.uniquindio.unieventos.model.vo.Location;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    private MultipartFile  posterImage;
    private MultipartFile locationImage;
    private String name;
    private String date;
    private String dateTime;
    private List<Location> listLocations;
    private EventType eventType;
    private EventStatus eventStatus;
}
