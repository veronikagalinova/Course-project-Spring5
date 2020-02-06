package bg.sofia.uni.fmi.tbb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Document(collection = "bus_lines")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class BusLine {
    @Id
    private String id;

    @NotNull
    @NonNull
    private Route route;

    @NotNull
    @NonNull
    @Min(0)
    private double price;

    @NotNull
    @NonNull
    @Min(0)
    private int seats;

    @JsonProperty(access = WRITE_ONLY)
    private String companyId;

    private String company;

    @NotNull
    @NonNull
    private List<DayOfWeek> workingDays;

    @NotNull
    @NonNull
    private String departureTime;
}
