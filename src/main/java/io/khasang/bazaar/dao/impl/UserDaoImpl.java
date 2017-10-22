package io.khasang.bazaar.dao.impl;

import io.khasang.bazaar.dao.RoleDao;
import io.khasang.bazaar.dao.UserDao;
import io.khasang.bazaar.entity.Role;
import io.khasang.bazaar.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * Implementation of {@link UserDao} interface
 *
 * @author Denis Tyurin
 * @version 1.0
 */
public class UserDaoImpl extends BasicDaoImpl<User> implements UserDao {
    @Autowired
    RoleDao roleDao;

    public UserDaoImpl(Class<User> entityClass) {
        super(entityClass);
    }

    @Override
    public User findUserByLogin(String login) {
        return (User) sessionFactory.getCurrentSession().
                createQuery("from User as u where u.login = ?").setParameter(0, login);
    }

    //TODO так привильно делать? Связываемся через Autowired?
    //TODO что-то в этом месте какая-то фигня... Надо HQL запрос реализовывать здесь по идее
    @Override
    public Set<User> findUsersByRole(String roleName) {
        Role role = roleDao.getRoleByName(roleName);
        return role.getUsers();
    }
}
