package bg.sofia.uni.fmi.tbb.web;

import bg.sofia.uni.fmi.tbb.domain.BusLinesService;
import bg.sofia.uni.fmi.tbb.model.BusLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/busLines")
public class BusLinesController {
    @Autowired
    private BusLinesService service;

    @GetMapping
    public List<BusLine> getBusLines() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public BusLine getBusLineById(@PathVariable("id") String id) {
        return service.findById(id);
    }

    @GetMapping("{company}")
    public List<BusLine> getBusLinesByCompany(@PathVariable("company") String company) {
        return service.findByCompany(company);
    }

    @GetMapping("{start}/{end}")
    public List<BusLine> getBusLinesByStartAndEndPoints(
            @PathVariable("start") String startPoint,
            @PathVariable("end") String endPoint) {
        return service.findByStartPointAndEndPoint(startPoint, endPoint);
    }

    @PostMapping
    public ResponseEntity<BusLine> createBusLine(@RequestBody BusLine busLine) {
        BusLine created = service.insert(busLine);
        URI location =
                MvcUriComponentsBuilder.fromMethodName(BusLinesController.class,
                        "createBusLine", BusLine.class).pathSegment("{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("{id}")
    public ResponseEntity<BusLine> updateUser(@PathVariable String id,
                                           @RequestBody BusLine busLine) {
        BusLine updated = service.update(busLine);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BusLine> remove(@PathVariable String id) {
        return ResponseEntity.ok(service.delete(id));
    }
}