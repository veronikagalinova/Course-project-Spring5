package bg.sofia.uni.fmi.tbb.domain;

import bg.sofia.uni.fmi.tbb.dto.BusLineSearchResult;
import bg.sofia.uni.fmi.tbb.model.BusLine;

import javax.validation.Valid;
import java.time.DayOfWeek;
import java.util.List;

public interface BusLinesService {
    List<BusLine> findAll();
    BusLine findById(String id);
    List<BusLine> findByCompany(String company);
    BusLine create(@Valid BusLine busLine);
    BusLine createIfNotExist(BusLine busLine);
    BusLine update(BusLine busLine);
    BusLine delete(String id);
    List<BusLineSearchResult> findRoute(String startPoint, String endPoint, DayOfWeek travelDay);
}
