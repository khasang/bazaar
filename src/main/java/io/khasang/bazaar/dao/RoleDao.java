package io.khasang.bazaar.dao;

import io.khasang.bazaar.entity.Role;

import java.util.List;

public interface RoleDao extends BasicDao<Role> {
//public interface RoleDao extends JpaRepository<Role, Long> {

    /**
     * Returns a role with the specified name
     *
     * @param roleName - role name
     * @return - a role with the specified name
     * @author - Denis Tyurin
     */
    Role getRoleByName(String roleName);

    /**
     * Returns a role with the specified role ID
     *
     * @param roleID - role ID (not an ID in the DB table, but a role ID)
     * @return - a role with the specified identifier
     * @author - Denis Tyurin
     */
    Role getRoleByRoleId(String roleID);

    /**
     * Returns a list of enabled or disabled roles
     *
     * @param isActive - role attribute that activates or deactivates it
     * @return - list of roles with the specified attribute (activated or deactivated)
     * @author - Denis Tyurin
     */
    List<Role> getRolesByIsActive(Integer isActive);
}
