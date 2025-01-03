/*
 * @Author: WZB 150590206+BJlaoma@users.noreply.github.com
 * @Date: 2024-10-15 23:39:46
 * @LastEditors: WZB 150590206+BJlaoma@users.noreply.github.com
 * @LastEditTime: 2024-10-28 12:24:52
 * @FilePath: \TripMap\src\main\java\com\TripMap\pojo\User.java
 */
package com.TripMap.pojo;
import java.util.UUID;

import org.bson.Document;
import org.bson.codecs.jsr310.LocalDateCodec;

import lombok.Data;

import java.time.LocalDate;
@Data
public class User {
    protected String uuid;//用户唯一标识符
    public String name;//用户名
    private String password;//密码
    private String avatarUrl;//用户头像储存地址
    private LocalDate createdAt;//创建用户的时间
    private LocalDate updatedAt;//上次更新用户时间

    public User(){}//数据库要求有默认构造函数
    public User(String name,String password){
        this.uuid = UUID.randomUUID().toString();
        this.name=name;
        this.password=password;
        createdAt=LocalDate.now();
        updatedAt=LocalDate.now();
        avatarUrl="";
    }
    public User(String uuid){
        this.uuid=uuid;
        this.name="";
        this.password="";
        this.avatarUrl="";
        this.createdAt=LocalDate.now();
        this.updatedAt=LocalDate.now();
    }
    public User(Document doc){
        this.uuid=doc.getString("uuid");
        this.name=doc.getString("name");
        this.password=doc.getString("password");
        this.avatarUrl=doc.getString("avatarUrl");
        this.createdAt=LocalDate.parse(doc.getString("createdAt"));
        this.updatedAt=LocalDate.parse(doc.getString("updatedAt"));
    }

    public String getUuid() {
        return uuid;
    }

    public void setPassword(String password,String newPassword){//修改密码需要原本的密码
        if(this.password==password){
            this.password=newPassword;
            System.out.println("修改成功");
            setUpdatedAt();
        }
        else{
            System.out.println("密码错误");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        setUpdatedAt();
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        setUpdatedAt();
        this.avatarUrl = avatarUrl;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt() {
        this.updatedAt = LocalDate.now();
    }
    
}
