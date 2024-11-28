package com.hm.servie.impl;

import com.hm.mapper.UserMapper;
import com.hm.pojo.User;
import com.hm.servie.UserService;
import com.hm.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.UIResource;
@Service
public class  UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String username) {
        User u =  userMapper.findByUserName(username);
        return u;
    }

    @Override
    public void register(String username, String password) {
        //密码加密
        String md5String = Md5Util.getMD5String(password);
        //添加
        userMapper.add(username,md5String);

    }
}
