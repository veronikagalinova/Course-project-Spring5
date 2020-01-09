package bg.sofia.uni.fmi.tbb.domain;

import bg.sofia.uni.fmi.tbb.dao.UsersRepository;
import bg.sofia.uni.fmi.tbb.exception.InvalidEntityException;
import bg.sofia.uni.fmi.tbb.exception.NonexistingEntityException;
import bg.sofia.uni.fmi.tbb.model.Role;

import static bg.sofia.uni.fmi.tbb.model.Role.ROLE_TRAVELER;

import bg.sofia.uni.fmi.tbb.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository repository;

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findById(String id) {
        return repository.findById(id).orElseThrow(() -> new NonexistingEntityException(
                String.format("User with ID='%s' does not exist.", id)
        ));
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(() -> new NonexistingEntityException(
                String.format("User with username='%s' does not exist.",
                        username)
        ));
    }

    @Override
    public User insert(User user) {
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(Arrays.asList(new Role(ROLE_TRAVELER)));
        }
        PasswordEncoder passwordEncoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        User created = repository.save(user);
        log.debug(">>>Created new user: " + created);
        return repository.insert(user);
    }

    @Override
    public User update(User user) {
        return repository.save(user);
    }

    @Override
    public User delete(String id) {
        Optional<User> old = repository.findById(id);
        if (!old.isPresent()) {
            throw new InvalidEntityException(String.format("User with id='%s'" +
                    " does not exist.", id));
        }
        repository.deleteById(id);
        return old.get();
    }

    @Override
    public long getSize() {
        return repository.count();
    }
}
