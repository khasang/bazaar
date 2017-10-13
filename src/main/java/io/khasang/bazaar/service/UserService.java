package io.khasang.bazaar.service;

import io.khasang.bazaar.entity.User;

import java.util.List;

public interface UserService {

    /**
     * Receive User by id
     *
     * @param id - user's id what we want to receive
     * @return user
     */
    User getById(Long id);

    /**
     * Create user at database
     *
     * @param user - user for creation
     * @return user
     */
    User addUser(User user);

    /**
     * Update user at database
     *
     * @param user - user for creation
     * @return user
     */
    User updateUser(User user);

    /**
     * Receive all users from database
     *
     * @return all users from database
     */
    List<User> getListUsers();

    /**
     * Delete user from database
     *
     * @param id - user's id for delete
     * @return user
     */
    User deleteUser(Long id);
}
