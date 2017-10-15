package io.khasang.bazaar.dao;

import io.khasang.bazaar.entity.Role;

import java.util.List;

public interface RoleDao extends BasicDao<Role> {
//public interface RoleDao extends JpaRepository<Role, Long> {
    public List<Role> getRolesByName(String roleName);

    public List<Role> getRolesByRoleId(String roleID);

    public List<Role> getRolesByIsActive(Boolean isActive);
}
