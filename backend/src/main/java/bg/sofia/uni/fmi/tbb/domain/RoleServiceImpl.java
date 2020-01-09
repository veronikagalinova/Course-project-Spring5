package bg.sofia.uni.fmi.tbb.domain;

import bg.sofia.uni.fmi.tbb.dao.RoleRepository;
import bg.sofia.uni.fmi.tbb.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository repository;

    @Override
    public List<Role> getRoles() {
        return repository.findAll();
    }

    @Override
    public Role createRoleIfNotExist(Role role) {
        Optional<Role> result = repository.findByName(role.getName());
        if(result.isPresent()) {
            return result.get();
        } else {
            log.info("Inserting new Role: {}", role);
            return repository.insert(role);
        }
    }

    @Override
    public Role updateRole(Role Role) {
        return repository.save(Role);
    }

    @Override
    public Optional<Role> getRoleByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public void deleteRole(Role role) {
        repository.delete(role);
    }
}
