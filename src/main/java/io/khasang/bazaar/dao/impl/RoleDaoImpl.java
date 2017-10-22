package io.khasang.bazaar.dao.impl;

import io.khasang.bazaar.dao.RoleDao;
import io.khasang.bazaar.entity.Role;

import java.util.List;

/**
 * Implementation of {@link RoleDao} interface
 *
 * @author Denis Tyurin
 * @version 1.0
 */
public class RoleDaoImpl extends BasicDaoImpl<Role> implements RoleDao {
    public RoleDaoImpl(Class<Role> entityClass) {
        super(entityClass);
    }

    public Role getRoleByName(String roleName) {
        return (Role) sessionFactory.getCurrentSession().
                createQuery("from Role as r where r.roleName = ?").setParameter(0, roleName);
    }

    public Role getRoleByRoleId(String roleId) {
        return (Role) sessionFactory.getCurrentSession().
                createQuery("from Role as r where r.roleId = ?").setParameter(0, Integer.parseInt(roleId));
    }

    public List<Role> getRolesByIsActive(Integer isActive) {
        //TODO: Warning unchecked cast List to List<Role> because createQuery(...) return has raw type, so result
        // of list is erased
        return (List<Role>) sessionFactory.getCurrentSession().
                createQuery("from Role as r where r.isActive = ?").setParameter(0, isActive).list();
    }

}