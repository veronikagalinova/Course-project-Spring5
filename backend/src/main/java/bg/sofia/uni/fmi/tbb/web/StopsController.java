package bg.sofia.uni.fmi.tbb.web;

import bg.sofia.uni.fmi.tbb.domain.StopsService;
import bg.sofia.uni.fmi.tbb.model.Stop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("api/stops")
public class StopsController {
    @Autowired
    private StopsService stopsService;

    @GetMapping
    public List<Stop> getTickets() {
        List<Stop> stops = stopsService.findAll();
        return stops;
    }
}
