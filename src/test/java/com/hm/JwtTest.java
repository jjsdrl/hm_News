package com.hm;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    @Test
    public void testGen() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "张三");
        //生成jwt的代码
        String token = JWT.create()
                .withClaim("user", claims)//添加成员
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))//添加过期时间
                .sign(Algorithm.HMAC256("zhy"));//指定算法配置密钥

        System.out.println(token);
    }

    @Test
    public void testParse(){
        //定义字符串,模拟用户传递过来的token
        String token ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IuW8oOS4iSJ9LCJleHAiOjE3MzE3OTgwMzh9.ffiOSdqzENhZH51M0bINMaUuUOE76IYk_x-U_R7p6tc";
        //jwt校验时使用的签名密钥,必须和生成jwt令牌时使用的密钥是配套的
        //如果jwt令牌解析校验时报错,则说明jwt令牌被篡改或失效了,令牌非法
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("zhy")).build();
        //验证token生成一个解析后的JWT对象
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        Map<String, Claim> claims = decodedJWT.getClaims();
        System.out.println(claims.get("user"));

        //篡改头部和载荷部分的数据,验证失败
        //密钥修改了,验证失败
        //token过期,验证失败

    }
}
