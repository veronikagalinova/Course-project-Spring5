package bg.sofia.uni.fmi.tbb.web;

import bg.sofia.uni.fmi.tbb.domain.TicketsService;
import bg.sofia.uni.fmi.tbb.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/tickets")
public class TicketsController {
    @Autowired
    private TicketsService ticketsService;

    @GetMapping
    public List<Ticket> getTickets() {
        return ticketsService.findAll();
    }

    @GetMapping("{userId}")
    public List<Ticket> findAllByUserId(@PathVariable("userId") String userId) {
        return ticketsService.findAllByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        Ticket created = ticketsService.insert(ticket);
        URI location =
                MvcUriComponentsBuilder.fromMethodName(TicketsController.class,
                        "createTicket", Ticket.class).pathSegment("{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable String id,
                                               @RequestBody Ticket ticket) {
        Ticket updated = ticketsService.update(ticket);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Ticket> remove(@PathVariable String id) {
        return ResponseEntity.ok(ticketsService.delete(id));
    }
}
