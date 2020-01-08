package bg.sofia.uni.fmi.tbb.dao;

import bg.sofia.uni.fmi.tbb.model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface TicketsRepository extends MongoRepository<Ticket, String> {
    Optional<List<Ticket>> findAllByUserId(String userId);
}
