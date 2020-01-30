package bg.sofia.uni.fmi.tbb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "bus_lines")
@Data
@AllArgsConstructor
public class BusLine {
    @Id
    private String id;
    private String startPoint;
    private String endPoint;
    private List<String> intermediateStops;

    @NotNull
    @Min(0)
    private double price;
    @NotNull
    @Min(0)
    private double distance;
    @NotNull
    @Min(0)
    private double duration;
    @NotNull
    @Min(0)
    private int seats;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String company;
    private String companyId;
}
