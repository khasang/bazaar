package io.khasang.bazaar.service;

import io.khasang.bazaar.entity.User;

import java.util.Set;

/**
 * Service class for {@link User}
 *
 * @author Denis Tyurin
 * @version 1.0
 */
public interface UserService {

    void saveUser(User user);

    User findUserByLogin(String login);

    Set<User> findUsersByRole(String roleName);
}