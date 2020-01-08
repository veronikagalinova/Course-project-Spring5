package bg.sofia.uni.fmi.tbb.domain;

import bg.sofia.uni.fmi.tbb.model.Ticket;

import java.util.List;

public interface TicketsService {
    List<Ticket> findAll();
    List<Ticket> findAllByUserId(String userId);
    Ticket insert(Ticket ticket);
    Ticket update(Ticket ticket);
    Ticket delete(String id);
}
