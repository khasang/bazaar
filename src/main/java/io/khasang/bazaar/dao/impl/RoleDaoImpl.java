package io.khasang.bazaar.dao.impl;

import io.khasang.bazaar.dao.RoleDao;
import io.khasang.bazaar.entity.Role;

import java.util.List;

public class RoleDaoImpl extends BasicDaoImpl<Role> implements RoleDao {
    public RoleDaoImpl(Class<Role> entityClass) {
        super(entityClass);
    }

    public List<Role> getRolesByName(String roleName) {
        //TODO: Warning unchecked cast List to List<Role> because createQuery(...) return has raw type, so result of list is erased
        return (List<Role>) sessionFactory.getCurrentSession().
                createQuery("from Role as r where r.roleName = ?").setParameter(0, roleName).list();
    }

    public List<Role> getRolesByRoleId(String roleId) {
        //TODO: Warning unchecked cast List to List<Role> because createQuery(...) return has raw type, so result of list is erased
        return (List<Role>) sessionFactory.getCurrentSession().
                createQuery("from Role as r where r.roleID = ?").setParameter(0, Integer.parseInt(roleId)).list();
    }

    public List<Role> getRolesByIsActive(Boolean isActive) {
        //TODO: Warning unchecked cast List to List<Role> because createQuery(...) return has raw type, so result of list is erased
        return (List<Role>) sessionFactory.getCurrentSession().
                createQuery("from Role as r where r.isactive = ?").setParameter(0, isActive.booleanValue()).list();
    }

}
