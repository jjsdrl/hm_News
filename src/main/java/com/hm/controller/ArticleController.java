package com.hm.controller;

import com.hm.pojo.Result;
import com.hm.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

//需要登录认证,没登录不能访问
//登录认证
//令牌就是一段字符串
//承载业务数据,减少后续请求查询数据库的次数
//防止篡改,保证信息的合法性和有效性
//jwt(JSON Web Token)Base64
//定义了一种简洁的,自包含的格式,用于通信双方以json数据格式安全的传输信息
//组成:
//第一部分:Header(头),记录令牌类型,签名算法等
//第二部分:Payload(有效载荷),携带一些自定义的信息,默认信息等,不存放私密信息
//第三部分(数字签名):Signature(签名),防止Token被篡改,确保安全性,将header,payload,并加入指定密钥,通过指定签名算法计算而来
@RestController
@RequestMapping("/article")
public class ArticleController {
    @GetMapping("/list")
    public Result<String> list(/*@RequestHeader(name = "Authorization") String token, HttpServletResponse response*/) {
        //验证token
//        try {
//            Map<String, Object> claims = JwtUtil.parseToken(token);
//            return Result.success("正在获取文章列表...");
//        } catch (Exception e) {
//            //http相应状态码为401
//            response.setStatus(401);
//            return Result.error("未登录");
//        }
        return Result.success("正在获取文章列表...");


    }
}
