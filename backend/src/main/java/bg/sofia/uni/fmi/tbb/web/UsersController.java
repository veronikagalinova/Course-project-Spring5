package bg.sofia.uni.fmi.tbb.web;

import bg.sofia.uni.fmi.tbb.domain.UsersService;
import bg.sofia.uni.fmi.tbb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/users")
public class UsersController {
    @Autowired
    private UsersService service;

    @GetMapping
    public List<User> getUsers() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public User findById(@PathVariable("id") String id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User created = service.insert(user);
        URI location =
                MvcUriComponentsBuilder.fromMethodName(UsersController.class,
                        "createUser", User.class).pathSegment("{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
        User updated = service.update(user);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> remove(@PathVariable String id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
