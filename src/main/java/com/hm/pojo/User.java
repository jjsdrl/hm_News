package com.hm.pojo;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


import java.time.LocalDateTime;
//lombok 在编译阶段，为实体类生成setter getter toString方法
//pom文件添加依赖，在实体类上添加注解
@Data
public class User {
    private Integer id;//主键ID
    private String username;//用户名
    @JsonIgnore//把当前对象转换为json时忽略此字段
    private String password;//密码
    private String nickname;//昵称
    private String email;//邮箱
    private String userPic;//用户头像地址
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间

}
