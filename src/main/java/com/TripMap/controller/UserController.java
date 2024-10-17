package com.TripMap.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TripMap.pojo.JsonResult;
import com.TripMap.pojo.User;
import com.TripMap.service.LoginService;
import com.TripMap.service.SignupService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.TripMap.mapper.Usermapper;

@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/signup")
    public JsonResult<User> Signup(@RequestBody JSONObject data) throws Exception{
        SignupService s=new SignupService();
        User user=s.Signup(data.getString("name"),data.getString("password"));
        return new JsonResult<User>(user);
    }

    @RequestMapping("/login")
    public JsonResult<User> Login(@RequestBody JSONObject data) throws Exception{
        LoginService s=new LoginService();
        User user=s.Login(data.getString("name"), data.getString("password"));
        return new JsonResult<User>(user);
    }

    @RequestMapping("/datatest")
    public String datatest(@RequestBody JSONObject data ){
        System.out.println(data.getString("name"));
        return "succees";
    }
}

