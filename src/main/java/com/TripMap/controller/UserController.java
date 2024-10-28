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
    /**
     * @function 微信登录
     * @param data 包含appid,secret,code,用户名，密码是用户的openid
     * @return JSONObject,包含openid,session_key
     * @throws Exception
     */
    @RequestMapping("/wxlogin")
    public JsonResult<JSONObject> Wxlogin(@RequestBody JSONObject data) throws Exception{
        LoginService s=new LoginService();
        JSONObject json=s.Wxlogin(data.getString("appid"), data.getString("secret"), data.getString("code"));
        User user=new User(data.getString("name"),json.getString("openid"));
        user.setAvatarUrl(data.getString("avatarUrl"));
        Usermapper mapper=new Usermapper();
        mapper.addUser(user);
        mapper.close();
        return new JsonResult<JSONObject>(json);
    }
}

