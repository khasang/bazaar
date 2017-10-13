package io.khasang.bazaar.service.impl;

import io.khasang.bazaar.dao.UserDao;
import io.khasang.bazaar.entity.User;
import io.khasang.bazaar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private  UserDao userDao;

    @Override
    public User deleteUser(Long id){
        User user = userDao.getById(id);
        return userDao.delete(user);
    }

    @Override
    public List<User> getListUsers() {
        return userDao.getList();
    }

    @Override
    public User addUser(User user) {
        return userDao.add(user);
    }

    @Override
    public User updateUser(User user) {
        return userDao.update(user);
    }

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }

}
