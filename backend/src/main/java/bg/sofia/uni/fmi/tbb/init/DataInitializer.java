package bg.sofia.uni.fmi.tbb.init;

import bg.sofia.uni.fmi.tbb.domain.UsersService;
import bg.sofia.uni.fmi.tbb.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Slf4j
@Component
public class DataInitializer implements ApplicationRunner {
    @Autowired
    private UsersService usersService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (usersService.getSize() == 0) {
            User user = new User(null, "admin", "admin123&", "Admin", "Admin",
                    Arrays.asList("ROLE_ADMIN"), true);
            log.info(">>>Created root admin user: {}", user);
            usersService.insert(user);
        }


    }
}
