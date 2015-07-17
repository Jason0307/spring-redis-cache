package org.zhubao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.zhubao.dao.UserDao;
import org.zhubao.model.User;
import org.zhubao.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.setBaseDao(userDao);
        this.userDao = userDao;
    }

    @Override
    @Cacheable(value = "userCache")
    public User findByName(String username) {
        System.out.println("No cache go here!" + " ====== findByName");
        User user = new User();
        user.setUsername(username);
        user.setUserId(1);
        return user;
    }

    @Override
    @Cacheable(value = "userListCache" )
    public List<User> getUsers() {
        System.out.println("No cache go here!" + " ====== getUsers");
        return userDao.findAll();
    }

    @Override
    @CachePut(value = "userListCache", key = "#user.username")
    public User saveUser(User user) {
        System.out.println("No cache go here!" + " ====== saveUser" );
        userDao.save(user);
        return user;
    }
}
