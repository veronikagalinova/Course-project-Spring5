package bg.sofia.uni.fmi.tbb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class NewTicketRequest {
    LocalDate travelDate;
    BusLineSearchResult route;
}
