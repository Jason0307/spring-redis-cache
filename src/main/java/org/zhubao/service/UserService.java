package org.zhubao.service;

import java.util.List;

import org.zhubao.model.User;

public interface UserService extends BaseService<User>{

    /**
     * find by username
     * @param username
     * @return
     */
    public User findByName(String username);
    
    /**
     * get users list
     * @return
     */
    public List<User> getUsers();

    /**
     * add user
     * @param user
     * @return
     */
    public User saveUser(User user);
}
