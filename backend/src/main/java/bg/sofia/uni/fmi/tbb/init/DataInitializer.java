package bg.sofia.uni.fmi.tbb.init;

import bg.sofia.uni.fmi.tbb.domain.BusLinesService;
import bg.sofia.uni.fmi.tbb.domain.StopsService;
import bg.sofia.uni.fmi.tbb.domain.UsersService;
import bg.sofia.uni.fmi.tbb.model.BusLine;
import bg.sofia.uni.fmi.tbb.model.Route;
import bg.sofia.uni.fmi.tbb.model.Stop;
import bg.sofia.uni.fmi.tbb.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.crypto.spec.PSource;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Component
public class DataInitializer implements ApplicationRunner {
    @Autowired
    private UsersService usersService;

    @Autowired
    private StopsService stopsService;

    @Autowired
    private BusLinesService busLinesService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (usersService.getSize() == 0) {
            User user = new User(null, "admin", "admin123&", "Admin", "Admin",
                    Arrays.asList("ROLE_ADMIN"), true);
            log.info(">>>Created root admin user: {}", user);
            usersService.insert(user);

            User traveler = new User("traveler", "traveler123&", "Veronika",
                    "Valentinova");
            usersService.insert(traveler);

            User busCompany = new User("company", "company123&", "Express",
                    "EOOD");
            busCompany.setRoles(Arrays.asList(User.BUS_COMPANY));
            busCompany = usersService.insert(busCompany);

            List<Stop> stops = createStops();
            insertBusLinesForCompany(busCompany, stops);
        }
    }

    private void insertBusLinesForCompany(User busCompany,
                                          List<Stop> stops) {
        Stop sofia = stops.get(7);
        Stop plovdiv = stops.get(3);
        List<DayOfWeek> workingDays = Arrays.asList(DayOfWeek.MONDAY,
                DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY,
                DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
        BusLine sofiaVarna = new BusLine(new Route(sofia, plovdiv, 120, 1.30),
                10.0, 20, workingDays, "09:00");
        sofiaVarna.setCompany(busCompany.getFirstName());
        sofiaVarna.setCompanyId(busCompany.getId());
        busLinesService.create(sofiaVarna);
    }

    private List<Stop> createStops() {
        List<String> locations = Arrays.asList("Sofia", "Vidin", "Varna",
                "Bourgas", "Pleven", "Lovech", "Plovdiv", "Veliko Tarnovo",
                "Sliven", "Yambol", "Razlog", "Ruse");
        List<Stop> stops =
                locations.stream()
                        .map(Stop::new)
                        .sorted(Comparator.comparing(Stop::getLocation))
                        .collect(Collectors.toList());
        stops.forEach(stop -> stopsService.insert(stop));
        return stops;
    }
}
