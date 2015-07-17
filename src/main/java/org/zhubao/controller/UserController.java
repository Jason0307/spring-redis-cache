package org.zhubao.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zhubao.model.User;
import org.zhubao.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getUsers() {
        return userService.getUsers();
    }
    
    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    @ResponseBody
    public User findUserByName(@PathVariable ("username") String username) {
        return userService.findByName(username);
    }
    
    @RequestMapping(value = "/user/add", method = RequestMethod.GET)
    @ResponseBody
    public User addUser() {
        User user = new User();
        user.setAge(27);
        user.setAvatar("http://192.168.206.10/icon/default_profile_pic.png");
        user.setDateCreated(new Date());
        user.setEmailAddress("baogee@vip.qq.com");
        user.setUsername("winfred");
        user.setPassword("123456");
        return userService.saveUser(user);
    }
    
    
}
