package org.example.service;

import org.example.dao.UserMapper;
import org.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    //注入UserMapper对象
    private UserMapper userMapper;
    @Override
    public User login(User user){
        return userMapper.login(user);
    }

}
