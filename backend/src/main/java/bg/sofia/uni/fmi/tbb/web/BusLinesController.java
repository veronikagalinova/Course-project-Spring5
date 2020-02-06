package bg.sofia.uni.fmi.tbb.web;

import bg.sofia.uni.fmi.tbb.domain.BusLinesService;
import bg.sofia.uni.fmi.tbb.metaannotations.IsBusCompanyOrAdmin;
import bg.sofia.uni.fmi.tbb.metaannotations.IsOwnerOrAdmin;
import bg.sofia.uni.fmi.tbb.model.BusLine;
import bg.sofia.uni.fmi.tbb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.core.Authentication;
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
    @PostFilter("filterObject.companyId == authentication.principal.id")
    public List<BusLine> getBusLines() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public BusLine getBusLineById(@PathVariable("id") String id) {
        return service.findById(id);
    }

    @PostMapping
    @IsBusCompanyOrAdmin
    public ResponseEntity<BusLine> createBusLine(@RequestBody BusLine busLine,
                                                 Authentication authentication) {
        User busLineUser = (User) authentication.getPrincipal();
        busLine.setCompanyId(busLineUser.getId());
        busLine.setCompany(busLineUser.getFirstName());
        BusLine created = service.createIfNotExist(busLine);
        URI location =
                MvcUriComponentsBuilder
                        .fromMethodName(BusLinesController.class,
                        "createBusLine", BusLine.class, Authentication.class)
                        .pathSegment("{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("{id}")
    @IsBusCompanyOrAdmin
    @IsOwnerOrAdmin
    public ResponseEntity<BusLine> updateBusLine(@PathVariable String id,
                                                 @RequestBody BusLine busLine) {
        BusLine updated = service.update(busLine);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("{id}")
    @IsBusCompanyOrAdmin
    @IsOwnerOrAdmin
    public ResponseEntity<BusLine> remove(@PathVariable String id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
