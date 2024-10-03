package com.example.unieventosbackend.model.documents;


import com.example.unieventosbackend.model.enums.EventStatus;
import com.example.unieventosbackend.model.enums.EventType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document("events")
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Event {

    @Id
    @EqualsAndHashCode.Include
    private String code;

    private String address;
    private String city;
    private String description;
    private String posterImage;
    private String locationImage;
    private String name;
    private LocalDateTime date;
    @Field("EventType")
    private EventType eventType;
    @Field("EventStatus")
    private EventStatus eventStatus;

}
