package bg.sofia.uni.fmi.tbb.dao;

import bg.sofia.uni.fmi.tbb.model.BusLine;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

@Repository
public interface BusLinesRepository extends MongoRepository<BusLine, String> {
    Optional<List<BusLine>> findAllByCompany(String company);

    @Query("{'route.startPoint.location': ?0, 'route.endPoint.location': ?1, 'workingDays': ?2}")
    Optional<List<BusLine>> findRoute(String startPoint, String endPoint, DayOfWeek travelDay);
}
