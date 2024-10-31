/*
 * @Author: WZB 150590206+BJlaoma@users.noreply.github.com
 * @Date: 2024-10-17 19:57:25
 * @LastEditors: WZB 150590206+BJlaoma@users.noreply.github.com
 * @LastEditTime: 2024-10-31 16:02:41
 * @FilePath: \TripMap\src\main\java\com\TripMap\controller\UserController.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
package com.TripMap.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.TripMap.pojo.JsonResult;
import com.TripMap.pojo.User;
import com.TripMap.service.LoginService;
import com.TripMap.service.SignupService;
import com.TripMap.service.Userservice;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.TripMap.mapper.Usermapper;

@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/signup")
    public JsonResult<User> Signup(@RequestBody JSONObject data) throws Exception{
        SignupService s=new SignupService();
        User user=s.Signup(data.getString("name"),data.getString("password"),data.getString("avatarUrl"));
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
     * @param data 包含appid,secret,code
     * @return JSONObject,包含openid,session_key
     * @throws Exception
     */
    @RequestMapping("/wxlogin")
    public JsonResult<JSONObject> Wxlogin(@RequestBody JSONObject data) throws Exception{
        LoginService s=new LoginService();
        JSONObject json=s.Wxlogin( "wx39efc4783f761db1", "24eaf3fd01e92b853c546514c3a02a9f", data.getString("code"));
        //User user=new User(data.getString("name"),json.getString("openid"));
        //user.setAvatarUrl(data.getString("avatarUrl"));
        //Usermapper mapper=new Usermapper();
        //mapper.addUser(user);
        //mapper.close();
        return new JsonResult<JSONObject>(json);
    }
    /**
     * @function 更新用户头像
     * @param file 头像文件
     * @param uuid 用户唯一标识符
     * @return JSONObject,包含uuid
     * @throws Exception
     */
    @RequestMapping("/updateavatar")
    public JsonResult<JSONObject> UpdateAvatar(@RequestParam("file") MultipartFile file, @RequestParam("uuid") String uuid) throws Exception {
        // 验证文件是否为空
        if (file.isEmpty()) {
            throw new Exception("上传的文件不能为空");
        }
        
        // 验证文件类型
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new Exception("只能上传图片文件");
        }
        
        Userservice s = new Userservice();
        JSONObject json = s.UpdateAvatar(uuid, file);
        return new JsonResult<JSONObject>(json);
    }

    /**
     * @function 更新用户信息
     * @param data 用户信息，现在只能修改名字
     * @return User对象
     * @throws Exception
     */
    @RequestMapping("/updateuser")
    public JsonResult<User> UpdateUser(@RequestBody JSONObject data) throws Exception{
        Userservice s=new Userservice();
        User user=s.UpdateUser(data.getString("uuid"), data);
        return new JsonResult<User>(user);
    }

    /**
     * @function 获取用户信息
     * @param uuid 用户唯一标识符
     * @return User对象
     * @throws Exception
     */
    @RequestMapping("/getuser")
    public JsonResult<User> GetUser(@RequestParam("uuid") String uuid) throws Exception{
        Usermapper mapper=new Usermapper();
        User user=mapper.getUserByUUID(uuid);
        return new JsonResult<User>(user);
    }
}

