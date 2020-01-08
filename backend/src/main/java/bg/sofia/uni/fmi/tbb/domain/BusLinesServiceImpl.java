package bg.sofia.uni.fmi.tbb.domain;

import bg.sofia.uni.fmi.tbb.dao.BusLinesRepository;
import bg.sofia.uni.fmi.tbb.exception.InvalidEntityException;
import bg.sofia.uni.fmi.tbb.exception.NonexistingEntityException;
import bg.sofia.uni.fmi.tbb.model.BusLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusLinesServiceImpl implements BusLinesService {
    @Autowired
    private BusLinesRepository repository;

    @Override
    public List<BusLine> findAll() {
        return repository.findAll();
    }

    @Override
    public BusLine findById(String id) {
        return repository.findById(id).orElseThrow(() -> new NonexistingEntityException(
                String.format("BusLine with ID='%s' does not exist.", id)
        ));
    }

    @Override
    public List<BusLine> findByCompany(String company) {
        return repository.findAllByCompany(company).orElseThrow(() -> new NonexistingEntityException(
                String.format("BusLines of company='%s' do not exist.", company)
        ));
    }

    @Override
    public List<BusLine> findByStartPointAndEndPoint(String startPoint,
                                                     String endPoint) {
        return repository.findAllByStartPointAndEndPoint(startPoint,
                endPoint).orElseThrow(() -> new NonexistingEntityException(
                String.format("BusLines from='%s' to='%s' do not exist.",
                        startPoint, endPoint)
        ));
    }

    @Override
    public BusLine insert(BusLine busLine) {
        return repository.insert(busLine);
    }

    @Override
    public BusLine update(BusLine busLine) {
        return repository.save(busLine);
    }

    @Override
    public BusLine delete(String id) {
        Optional<BusLine> old = repository.findById(id);
        if (!old.isPresent()) {
            throw new InvalidEntityException(String.format("BusLine with id='%s'" +
                    " does not exist.", id));
        }
        repository.deleteById(id);
        return old.get();
    }
}
