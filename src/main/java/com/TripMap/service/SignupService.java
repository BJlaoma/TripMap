package com.TripMap.service;

import org.springframework.stereotype.Service;

import com.TripMap.pojo.User;
//注册
@Service
public class SignupService {
    public void Signup(String name,String password){
        User user=new User(name, password);
    }
}
