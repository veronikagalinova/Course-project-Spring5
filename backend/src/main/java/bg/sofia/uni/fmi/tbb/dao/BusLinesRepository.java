package bg.sofia.uni.fmi.tbb.dao;

import bg.sofia.uni.fmi.tbb.model.BusLine;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface BusLinesRepository extends MongoRepository<BusLine, String> {
    Optional<List<BusLine>> findAllByCompany(String company);
    Optional<List<BusLine>> findAllByStartPointAndEndPoint(String startPoint, String endPoint);
}
