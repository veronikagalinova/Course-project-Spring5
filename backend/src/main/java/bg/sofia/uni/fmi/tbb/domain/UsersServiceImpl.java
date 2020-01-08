package bg.sofia.uni.fmi.tbb.domain;

import bg.sofia.uni.fmi.tbb.dao.UsersRepository;
import bg.sofia.uni.fmi.tbb.exception.InvalidEntityException;
import bg.sofia.uni.fmi.tbb.exception.NonexistingEntityException;
import bg.sofia.uni.fmi.tbb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
        // TO DO - roles, password encoding
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
}
