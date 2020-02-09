package bg.sofia.uni.fmi.tbb.domain.impl;

import bg.sofia.uni.fmi.tbb.dao.BusLinesRepository;
import bg.sofia.uni.fmi.tbb.domain.BusLinesService;
import bg.sofia.uni.fmi.tbb.dto.BusLineSearchResult;
import bg.sofia.uni.fmi.tbb.exception.InvalidEntityException;
import bg.sofia.uni.fmi.tbb.exception.NonexistingEntityException;
import bg.sofia.uni.fmi.tbb.model.BusLine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
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
    public BusLine create(@Valid BusLine busLine) {
        return repository.insert(busLine);
    }

    @Override
    public BusLine createIfNotExist(@Valid BusLine busLine) {
        if (busLine.getId() == null) {
            return repository.insert(busLine);
        }
        Optional<BusLine> result = repository.findById(busLine.getId());
        if (result.isPresent()) {
            return result.get();
        } else {
            log.info("Inserting new Bus Line: {}", busLine);
            return repository.insert(busLine);
        }
    }

    @Override
    public BusLine update(BusLine busLine) {
        log.info("Updating line " + busLine.getId() + " new line is {}",
                busLine);
        return repository.save(busLine);
    }

    @Override
    public BusLine delete(String id) {
        Optional<BusLine> old = repository.findById(id);
        if (!old.isPresent()) {
            throw new InvalidEntityException(String.format("BusLine with " +
                    "id='%s'" +
                    " does not exist.", id));
        }
        repository.deleteById(id);
        return old.get();
    }

    @Override
    public List<BusLineSearchResult> findRoute(String startPoint,
                                               String endPoint,
                                               DayOfWeek travelDay) {
        List<BusLineSearchResult> result = new ArrayList<>();
        Optional<List<BusLine>> found = repository.findRoute(startPoint,
                endPoint, travelDay);
        if (found.isPresent()) {
            result = found.get().stream()
                    .map(b -> {
                        BusLineSearchResult converted =
                                new BusLineSearchResult(
                                        b.getRoute().getStartPoint().getLocation(),
                                        b.getRoute().getStartPoint().getLocation(),
                                        b.getCompany(),
                                        b.getDepartureTime(), "CHANGE THIS MOCK",
                                        b.getPrice(),
                                        b.getRoute().getDuration());
                        return converted;
                    }).collect(Collectors.toList());
        }
        return result;
    }
}
