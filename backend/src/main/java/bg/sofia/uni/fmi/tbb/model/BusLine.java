package bg.sofia.uni.fmi.tbb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Document(collection = "bus_lines")
@Data
@AllArgsConstructor
public class BusLine {
    @Id
    private String id;
    @NotNull
    @NonNull
    private String startPoint;
    @NotNull
    @NonNull
    private String endPoint;
    @NotNull
    @NonNull
    @Min(0)
    private double price;
    @NotNull
    @NonNull
    @Min(0)
    private double distance;
    @NotNull
    @NonNull
    @Min(0)
    private double duration;
    @NotNull
    @NonNull
    @Min(0)
    private int seats;
    @JsonProperty(access = WRITE_ONLY)
    private String companyId;
    @NotNull
    @NonNull
    private LocalDateTime departureTime;
    @NotNull
    @NonNull
    private LocalDateTime arrivalTime;
    private String company;
    @JsonProperty(access = WRITE_ONLY)
    private List<String> travelerIds;
}
