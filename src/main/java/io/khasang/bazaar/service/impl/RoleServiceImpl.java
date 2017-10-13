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

    @Override
    public Role getById(Long id) {
        return null;
    }

    @Override
    public Role addRole(Role role) {
        return null;
    }

    @Override
    public Role updateRole(Role role) {
        return null;
    }

    @Override
    public List<Role> getRoleList() {
        return null;
    }

    @Override
    public List<Role> getRolesByName(String name) {
        return roleDao.getList();
    }

    @Override
    public Role deleteRole(Long id) {
        return null;
    }
}
