package bg.sofia.uni.fmi.tbb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection = "tickets")
@Data
@AllArgsConstructor
public class Ticket {

    @Id
    private String id;
    private String startPoint;
    private String endPoint;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String company;
    private String seat;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String userId;
}
