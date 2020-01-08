package bg.sofia.uni.fmi.tbb.dao;

import bg.sofia.uni.fmi.tbb.model.BusLine;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface BusLinesRepository extends MongoRepository<BusLine, String> {
}
