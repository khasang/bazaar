package io.khasang.bazaar.service;

import io.khasang.bazaar.entity.Role;
import java.util.List;

/**
 * Service class for {@link Role}
 *
 * @author Denis Tyurin
 * @version 1.0
 */
public interface RoleService {
    /**
     * Returns a role by id in the DB table
     *
     * @param id - role's id in the DB table
     * @return role
     */
    Role getRoleById(Long id);

    /**
     * Creates a role in the DB
     *
     * @param role - role for creation
     * @return created role
     */
    Role addRole(Role role);

    /**
     * Updates role at DB
     *
     * @param role - role for updating
     * @return updated role
     */
    Role updateRole(Role role);

    /**
     * Returns all roles from DB
     *
     * @return all roles from DB
     */
    List<Role> getAllRoles();

    /**
     * Returns a role from DB with specified name
     *
     * @param roleName - role's name
     * @return a role with specified name
     */
    Role getRoleByName(String roleName);

    /**
     * Deletes a role by id in the DB table
     *
     * @param id - role's id in the DB table for delete
     * @return deleted role
     */
    Role deleteRole(Long id);

    /**
     * Returns a role with specified roleId
     *
     * @param roleId - role's id (not an ID in the DB table, but a role ID)
     * @return role with the specified roleId
     */
    Role getRoleByRoleId(String roleId);

    /**
     * Returns a list of enabled or disabled roles
     *
     * @param isActive - role attribute that activates or deactivates it
     * @return - list of roles with the specified attribute (activated or deactivated)
     * @author - Denis Tyurin
     */
    List<Role> getRolesByIsActive(Integer isActive);
}
