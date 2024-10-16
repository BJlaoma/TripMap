package com.TripMap.service;

import org.springframework.stereotype.Service;
import java.util.UUID;

import com.TripMap.mapper.Usermapper;
import com.TripMap.pojo.User;
//注册
@Service
public class SignupService {
    public UUID Signup(String name,String password) throws Exception{
        /*
         * 询问数据库是否已经有相同名字的用户
         */
        Usermapper mapper=new Usermapper();
        if(mapper.foundUser(name)){
            throw new Exception("用户名已被使用");
        }
        User user=new User(name, password);
        /*
         * 将新用户添加到数据库
         */
        mapper.addUser(user);
        mapper.close();
        return user.getUuid();
    }
}
