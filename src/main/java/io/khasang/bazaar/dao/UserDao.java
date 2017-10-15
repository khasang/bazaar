package io.khasang.bazaar.dao;

import io.khasang.bazaar.entity.User;

public interface UserDao extends BasicDao<User> {
    User findByUserName(String username);
}