package io.khasang.bazaar.service.impl;

import io.khasang.bazaar.dao.RoleDao;
import io.khasang.bazaar.entity.Role;
import io.khasang.bazaar.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link RoleService} interface.
 *
 * @author Denis Tyurin
 * @version 1.0
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    public RoleServiceImpl() {
    }

    @Override
    public Role getRoleById(Long id) {
        return roleDao.getById(id);
    }

    @Override
    public Role addRole(Role role) {
        return roleDao.add(role);
    }

    @Override
    public Role updateRole(Role role) {
        return roleDao.update(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getList();
    }

    @Override
    public Role deleteRole(Long id) {
        return roleDao.delete(roleDao.getById(id));
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleDao.getRoleByName(roleName);
    }

    @Override
    public Role getRoleByRoleId(String roleId) {
        return roleDao.getRoleByRoleId(roleId);
    }

    @Override
    public List<Role> getRolesByIsActive(Integer isActive) {
        return roleDao.getRolesByIsActive(isActive);
    }


}