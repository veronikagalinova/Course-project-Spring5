package bg.sofia.uni.fmi.tbb.domain;

import bg.sofia.uni.fmi.tbb.dao.UsersRepository;
import bg.sofia.uni.fmi.tbb.exception.InvalidEntityException;
import bg.sofia.uni.fmi.tbb.exception.NonexistingEntityException;

import bg.sofia.uni.fmi.tbb.metaannotations.IsAdmin;
import bg.sofia.uni.fmi.tbb.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UsersServiceImpl implements UsersService {

    private static final String EXISTING_USER_ERROR_MSG = "User %s already exist!";

    @Autowired
    private UsersRepository repository;

    @Override
    @IsAdmin
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
    public User insert(@Valid User user) {
        Optional<User> userInDb = repository.findByUsername(user.getUsername());
        if (userInDb.isPresent()) {
            throw new InvalidEntityException(
                    String.format(EXISTING_USER_ERROR_MSG, user.getUsername()));
        }

        setRoles(user);
        PasswordEncoder passwordEncoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        User created = repository.insert(user);
        log.debug(">>>Created new user: " + created);
        return created;
    }

    @Override
    public User update(User user) {
        setRoles(user);
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

    private void setRoles(User user) {
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(Arrays.asList("ROLE_TRAVELER"));
        }
    }
}
