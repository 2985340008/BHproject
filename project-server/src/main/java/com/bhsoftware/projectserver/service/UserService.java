package com.bhsoftware.projectserver.service;

import com.bhsoftware.projectserver.dao.UserDao;
import com.bhsoftware.projectserver.entity.User;
import com.bhsoftware.projectserver.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserMapper userMapper;

    public boolean isExist(String username){
        User user = getByName(username);
        return null!=user;
    }

    public User getByName(String username){
        return userDao.findByUsername(username);
    }

    public User get(String username,String password){
       return userDao.getByUsernameAndPassword(username, password);
    }

    public void add(User user){
        userDao.save(user);
    }

    public void register(User user){
        userMapper.register(user);
    }
}
