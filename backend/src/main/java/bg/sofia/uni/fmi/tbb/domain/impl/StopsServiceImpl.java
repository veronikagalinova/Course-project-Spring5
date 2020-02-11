package bg.sofia.uni.fmi.tbb.domain.impl;

import bg.sofia.uni.fmi.tbb.dao.StopsRepository;
import bg.sofia.uni.fmi.tbb.domain.StopsService;
import bg.sofia.uni.fmi.tbb.exception.InvalidEntityException;
import bg.sofia.uni.fmi.tbb.exception.NonexistingEntityException;
import bg.sofia.uni.fmi.tbb.model.Stop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StopsServiceImpl implements StopsService {
    @Autowired
    private StopsRepository repository;

    @Override
    public List<Stop> findAll() {
        return repository.findAll();
    }

    @Override
    public Stop findById(String id) {
        return repository.findById(id).orElseThrow(() -> new NonexistingEntityException(
                String.format("Stop with id='%s' do not exist.", id)
        ));
    }

    @Override
    public Stop findByLocation(String location) {
         return repository.findByLocation(location).orElseThrow(() -> new NonexistingEntityException(
                String.format("Stop with location='%s' do not exist.", location)
        ));
    }

    @Override
    public Stop insert(Stop stop) {
        return repository.insert(stop);
    }

    @Override
    public Stop update(Stop stop) {
        return repository.save(stop);
    }

    @Override
    public Stop delete(String id) {
        Optional<Stop> old = repository.findById(id);
        if (!old.isPresent()) {
            throw new InvalidEntityException(String.format("Stop with id='%s'" +
                    " does not exist.", id));
        }
        repository.deleteById(id);
        return old.get();
    }
}
