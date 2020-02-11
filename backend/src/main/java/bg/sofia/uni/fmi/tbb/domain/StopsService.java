package bg.sofia.uni.fmi.tbb.domain;

import bg.sofia.uni.fmi.tbb.model.Stop;

import java.util.List;

public interface StopsService {
    List<Stop> findAll();
    Stop findById(String id);
    Stop findByLocation(String location);
    Stop insert(Stop stop);
    Stop update(Stop stop);
    Stop delete(String id);
}
