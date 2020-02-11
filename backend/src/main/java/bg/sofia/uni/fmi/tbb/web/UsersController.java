package bg.sofia.uni.fmi.tbb.web;

import bg.sofia.uni.fmi.tbb.domain.UsersService;
import bg.sofia.uni.fmi.tbb.metaannotations.IsAdmin;
import bg.sofia.uni.fmi.tbb.metaannotations.IsCurrentUserOrAdmin;
import bg.sofia.uni.fmi.tbb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static bg.sofia.uni.fmi.tbb.model.User.ADMIN;
import static bg.sofia.uni.fmi.tbb.model.User.BUS_COMPANY;
import static bg.sofia.uni.fmi.tbb.model.User.TRAVELER;

@RestController
@RequestMapping("api/users")
public class UsersController {
    @Autowired
    private UsersService service;

    @GetMapping
    @IsAdmin
    public List<User> getUsers() {
        return service.findAll();
    }

    @GetMapping("{id}")
    @IsCurrentUserOrAdmin
    public User findById(@PathVariable("id") String id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid User user,
                                           Authentication authentication) {
//        if (!areRolesValid(user, authentication)) {
//            throw new IllegalArgumentException("Provided user roles are not " +
//                    "valid!");
//        }

        User created = service.insert(user);
        URI location =
                MvcUriComponentsBuilder.fromMethodName(UsersController.class,
                        "createUser", User.class, Authentication.class).pathSegment("{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

//    private boolean areRolesValid(User user, Authentication authentication) {
//        if (user.getRoles() == null || user.getRoles().isEmpty())
//            return true;
//        User principal = (User) authentication.getPrincipal();
//        if (principal == null || principal.getRoles() == null)
//            return true;
//
//        List<String> roles = user.getRoles();
//        return principal.getRoles().contains(ADMIN) ?
//                user.getValidRoles().containsAll(roles) :
//                roles.size() == 1 && user.getValidRoles().contains(roles.get(0));
//    }

    @PutMapping("{id}")
    @IsCurrentUserOrAdmin
    public ResponseEntity<User> updateUser(@PathVariable String id,
                                           @RequestBody User user) {
        User updated = service.update(user);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("{id}")
    @IsCurrentUserOrAdmin
    public ResponseEntity<User> remove(@PathVariable String id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
