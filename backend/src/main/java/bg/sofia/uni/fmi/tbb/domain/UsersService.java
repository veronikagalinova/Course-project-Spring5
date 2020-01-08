package bg.sofia.uni.fmi.tbb.domain;

import bg.sofia.uni.fmi.tbb.model.User;

import java.util.List;

public interface UsersService {
    List<User> findAll();
    User findById(String id);
    User findByUsername(String username);
    User insert(User user);
    User update(User user);
    User delete(String id);
}
