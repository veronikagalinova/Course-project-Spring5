package bg.sofia.uni.fmi.tbb.web;

import bg.sofia.uni.fmi.tbb.domain.BusLinesService;
import bg.sofia.uni.fmi.tbb.domain.TicketsService;
import bg.sofia.uni.fmi.tbb.dto.NewTicketRequest;
import bg.sofia.uni.fmi.tbb.exception.OutOfSeatsException;
import bg.sofia.uni.fmi.tbb.metaannotations.IsTraveler;
import bg.sofia.uni.fmi.tbb.model.Ticket;
import bg.sofia.uni.fmi.tbb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("api/tickets")
public class TicketsController {
    @Autowired
    private TicketsService ticketsService;

    @Autowired
    private BusLinesService busLinesService;

    @GetMapping
    @IsTraveler
    @PostFilter("filterObject.userId == authentication.principal.id")
    public List<Ticket> getTickets() {
        return ticketsService.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Ticket> findTicketById(@PathVariable("id") String id) {
        return ResponseEntity.ok(ticketsService.findById(id));
    }

    @PostMapping
    @IsTraveler
    public ResponseEntity<Ticket> buyTicket(@RequestBody NewTicketRequest request,
                                            Authentication authentication) throws OutOfSeatsException {
        Ticket ticket = createTicketForBuyRequest(request);
        ticket.setUserId(((User) authentication.getPrincipal()).getId());
        Ticket created = ticketsService.insert(ticket);
        URI location =
                MvcUriComponentsBuilder.fromMethodName(TicketsController.class,
                        "buyTicket", NewTicketRequest.class,
                        Authentication.class).pathSegment("{id}").buildAndExpand(created.getId()).toUri();
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

    private Ticket createTicketForBuyRequest(NewTicketRequest request) throws OutOfSeatsException {
        Ticket ticket = new Ticket();
        ticket.setStartPoint(request.getRoute().getStartPoint());
        ticket.setEndPoint(request.getRoute().getEndPoint());

        LocalDateTime departure =
                getDeparturePresentation(request.getRoute().getDepartureTime(), request.getTravelDate());
        ticket.setDepartureTime(departure.format(DateTimeFormatter.ofPattern(
                "dd MMM uuuu HH:mm")));


        LocalDateTime arrival =
                getArrivalRepresentation(request.getRoute().getDuration(),
                        departure);
        ticket.setArrivalTime(arrival.format(DateTimeFormatter.ofPattern("dd " +
                "MMM uuuu HH:mm")));

        ticket.setCompany(request.getRoute().getCompany());

        int seat =
                busLinesService.findSeatForTravelerTicket(request.getRoute().getLineId());
        ticket.setSeat(seat);
        return ticket;
    }

    private LocalDateTime getDeparturePresentation(String departureStr,
                                                   LocalDate travelDate) {
        String[] departureHour = departureStr.split(":");
        LocalTime hour = LocalTime.of(Integer.parseInt(departureHour[0]),
                Integer.parseInt(departureHour[1]));
        LocalDateTime departure = LocalDateTime.of(travelDate, hour);

        return departure;
    }

    private LocalDateTime getArrivalRepresentation(double durationStr,
                                                   LocalDateTime departure) {
        String duration = String.valueOf(durationStr);
        int indexOfDecimal = duration.indexOf(".");
        if (indexOfDecimal == -1) {
            return departure.plusHours(Long.parseLong(duration));
        }
        LocalDateTime arrival =
                departure.plusHours(Long.parseLong(duration.substring(0,
                        indexOfDecimal)));
        arrival = arrival.plusMinutes(Long.parseLong(duration.substring(indexOfDecimal + 1)));
        return arrival;
    }
}
