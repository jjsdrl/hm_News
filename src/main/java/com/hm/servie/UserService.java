package com.hm.servie;

import com.hm.pojo.User;

public interface UserService {
    //根据用户名查询
    User findByUserName(String username);
    //注册
    void register(String username, String password);
}
