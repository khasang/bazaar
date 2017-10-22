package io.khasang.bazaar.dao;

import io.khasang.bazaar.entity.Role;
import io.khasang.bazaar.entity.User;

import java.util.Set;

public interface UserDao extends BasicDao<User> {
    /**
     * Returns the user with the specified login (it is assumed that the login is unique for each user)
     *
     * @param login - user's login
     * @return - a user with the specified login
     * @author - Denis Tyurin
     */
    User findUserByLogin(String login);

    /**
     * Returns a list of users which have the specified {@link Role}
     *
     * @param roleName - name of role
     * @return - a list of the users with the specified role
     * @author - Denis Tyurin
     */
    Set<User> findUsersByRole(String roleName);
}