package com.hm.controller;

import com.hm.pojo.Result;
import com.hm.pojo.User;
import com.hm.servie.UserService;
import com.hm.utils.JwtUtil;
import com.hm.utils.Md5Util;
import com.hm.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^[a-zA-Z0-9]{5,16}$") String username,@Pattern(regexp = "^[a-zA-Z0-9]{5,16}$") String password) {

//        //判断用户名和密码是否合法
//        if (username !=null && username.length()>=5 &&  username.length()<16 &&
//                password !=null && password.length()>=5 &&  password.length()<16) {
//            //查询用户
//            User u =    userService.findByUserName(username);
//            if (u == null) {
//                //没有占用
//                userService.register(username,password);
//                return Result.success();
//            }else {
//                //占用
//                return Result.error("用户名被占用");
//            }
//        }else {
//            return Result.error("用户名或密码格式错误");
//        }
        User u = userService.findByUserName(username);
        if (u == null) {
            //没有占用
            userService.register(username,password);
            return Result.success();
        }else {
            //占用
            return Result.error("用户名被占用");
        }
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^[^\\s]{5,16}$") String username,@Pattern(regexp = "^[^\\s]{5,16}$") String password) {
        //根据用户名查询用户
        User loginUser = userService.findByUserName(username);
        //判断用户是否 存在
        if (loginUser == null) {
            return Result.error("用户名错误");
        }
        //判断密码是否正确
        if(Md5Util.getMD5String(password).equals(loginUser.getPassword())){
            //登录成功

            //创建token
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",loginUser.getId());
            claims.put("username",loginUser.getUsername());
            String token = JwtUtil.genToken(claims);

            return Result.success(token);
        }
        return Result.error("密码错误");
     }

     @GetMapping("userInfo")
     //从请求头中获取token
     //ThreadLocall:提供线程局部变量
     //  用来存取数据:set()//get()
     //  使用ThreadLocall存储的数据,线程安全
//     public Result<User> userInfo(@RequestHeader(name="Authorization") String token) {
     public Result<User> userInfo() {
        //根据用户名查询用户
//        Map<String, Object> map = JwtUtil.parseToken(token);
//        String username =(String) map.get("username");
        Map<String, Object> map = ThreadLocalUtil.get();
        String username =(String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
     }
}
