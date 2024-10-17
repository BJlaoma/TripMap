package com.TripMap.service;

import java.util.UUID;
import org.springframework.stereotype.Service;

import com.TripMap.mapper.Usermapper;
import com.TripMap.pojo.User;
//用户登录
@Service
public class LoginService {
    public User Login(String name,String password) throws Exception{
        
        /*
         * 向数据库查找，登录成功返回用户对象,不成功抛出错误
         */
        Usermapper mapper=new Usermapper();
        User user=mapper.foundUser(name, password);
        mapper.close();
        return user;
    }
}
