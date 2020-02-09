package bg.sofia.uni.fmi.tbb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BusLineSearchResult {
    private String startPoint;
    private String endPoint;
    private String company;
    private String departureTime;
    private String arrivalTime;
    private double price;
    private double duration;
}
