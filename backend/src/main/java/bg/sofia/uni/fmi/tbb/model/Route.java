package bg.sofia.uni.fmi.tbb.model;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Route {

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
