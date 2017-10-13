package io.khasang.bazaar.service;

import io.khasang.bazaar.entity.Role;
import java.util.List;

public interface RoleService {
    /**
     * Receive Role by id at DB
     *
     * @param id - role's id for receive from DB
     * @return role
     */
    Role getById(Long id);

    /**
     * Create Role at DB
     *
     * @param role - role for creation
     * @return role
     */
    Role addRole(Role role);

    /**
     * Update role at DB
     *
     * @param role - cat for creation
     * @return role
     */
    Role updateRole(Role role);

    /**
     * Receive all roles from DB
     *
     * @return all roles from DB
     */
    List<Role> getRoleList();

    /**
     * Receive role from DB with specified name
     *
     * @param name - role's name
     * @return list of roles with specified name
     */
    List<Role> getRolesByName(String name);

    /**
     * Delete role from DB
     *
     * @param id - role's id for delete
     * @return role
     */
    Role deleteRole(Long id);

}
