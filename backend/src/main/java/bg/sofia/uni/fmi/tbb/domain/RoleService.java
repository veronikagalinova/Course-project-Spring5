package bg.sofia.uni.fmi.tbb.domain;


import bg.sofia.uni.fmi.tbb.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role>  getRoles();
    Role createRoleIfNotExist(Role role);
    Role updateRole(Role role);
    Optional<Role> getRoleByName(String id);
    void deleteRole(Role role);
}
