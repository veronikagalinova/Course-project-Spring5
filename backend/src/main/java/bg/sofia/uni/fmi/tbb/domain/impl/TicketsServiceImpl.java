package bg.sofia.uni.fmi.tbb.domain.impl;

import bg.sofia.uni.fmi.tbb.dao.TicketsRepository;
import bg.sofia.uni.fmi.tbb.domain.TicketsService;
import bg.sofia.uni.fmi.tbb.exception.InvalidEntityException;
import bg.sofia.uni.fmi.tbb.exception.NonexistingEntityException;
import bg.sofia.uni.fmi.tbb.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketsServiceImpl implements TicketsService {
    @Autowired
    private TicketsRepository repository;

    @Override
    public List<Ticket> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Ticket> findAllByUserId(String userId) {
        return repository.findAllByUserId(userId).orElseThrow(() -> new NonexistingEntityException(
                String.format("Tickets for user with id='%s' do not exist.", userId)
        ));
    }

    @Override
    public Ticket insert(Ticket ticket) {
        return repository.insert(ticket);
    }

    @Override
    public Ticket update(Ticket ticket) {
        return repository.save(ticket);
    }

    @Override
    public Ticket delete(String id) {
        Optional<Ticket> old = repository.findById(id);
        if (!old.isPresent()) {
            throw new InvalidEntityException(String.format("Ticket with id='%s'" +
                    " does not exist.", id));
        }
        repository.deleteById(id);
        return old.get();
    }
}
