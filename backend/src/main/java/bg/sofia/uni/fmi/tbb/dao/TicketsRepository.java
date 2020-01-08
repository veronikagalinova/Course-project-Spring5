package bg.sofia.uni.fmi.tbb.dao;

import bg.sofia.uni.fmi.tbb.model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketsRepository extends MongoRepository<Ticket, String> {
}
