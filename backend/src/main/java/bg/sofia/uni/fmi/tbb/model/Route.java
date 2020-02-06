package bg.sofia.uni.fmi.tbb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Document(collection = "routes")
@Data
@AllArgsConstructor
public class Route {
    @Id
    private String id;

    @NotNull
    @NonNull
    private Stop startPoint;

    @NotNull
    @NonNull
    private Stop endPoint;

    @NotNull
    @NonNull
    @Min(0)
    private double distance;

    @NotNull
    @NonNull
    @Min(0)
    private double duration;

    private List<Stop> intermediateStops;

}
