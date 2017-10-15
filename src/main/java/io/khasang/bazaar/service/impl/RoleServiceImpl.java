package io.khasang.bazaar.service.impl;

import io.khasang.bazaar.dao.RoleDao;
import io.khasang.bazaar.entity.Role;
import io.khasang.bazaar.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    public RoleServiceImpl() {
    }

    @Override
    public Role getById(Long id) {
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
    public List<Role> getRoleList() {
        return roleDao.getList();
    }

    @Override
    public Role deleteRole(Long id) {
        return roleDao.delete(roleDao.getById(id));
    }

    public List<Role> getRolesByName(String roleName) {
        return roleDao.getRolesByName(roleName);
    }

    @Override
    public List<Role> getRolesByRoleId(String roleId) {
        return roleDao.getRolesByRoleId(roleId);
    }

    @Override
    public List<Role> getRolesByIsActive(Boolean isActive) {
        return roleDao.getRolesByIsActive(isActive);
    }


}