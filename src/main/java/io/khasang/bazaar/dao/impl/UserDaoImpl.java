package io.khasang.bazaar.dao.impl;

import io.khasang.bazaar.dao.UserDao;
import io.khasang.bazaar.entity.User;

public class UserDaoImpl extends BasicDaoImpl<User> implements UserDao {
    public UserDaoImpl(Class<User> entityClass) {
        super(entityClass);
    }

}
