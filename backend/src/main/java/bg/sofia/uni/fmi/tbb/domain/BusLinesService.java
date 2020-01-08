package bg.sofia.uni.fmi.tbb.domain;

import bg.sofia.uni.fmi.tbb.model.BusLine;
import bg.sofia.uni.fmi.tbb.model.Ticket;

import java.util.List;

public interface BusLinesService {
    List<BusLine> findAll();
    BusLine findById(String id);
    List<BusLine> findByCompany(String company);
    List<BusLine> findByStartPointAndEndPoint(String startPoint, String endPoint);
    BusLine insert(BusLine busLine);
    BusLine update(BusLine busLine);
    BusLine delete(String id);
}
