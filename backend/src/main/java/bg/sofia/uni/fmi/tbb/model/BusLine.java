package bg.sofia.uni.fmi.tbb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "bus_lines")
@Data
@AllArgsConstructor
public class BusLine {

    @Id
    private String id;

    private String company;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String companyId;

    private String startPoint;

    private String endPoint;

    private List<String> intermediateStops;

    private double price;

    private double distance;

    private double duration;

    private int seats;
}
