/*
 * @Author: WZB 150590206+BJlaoma@users.noreply.github.com
 * @Date: 2024-10-12 17:05:53
 * @LastEditors: WZB 150590206+BJlaoma@users.noreply.github.com
 * @LastEditTime: 2024-10-31 14:41:34
 * @FilePath: \TripMap\src\main\java\com\TripMap\service\LoginService.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
package com.TripMap.service;

import java.net.http.HttpResponse;
import java.util.UUID;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import org.springframework.stereotype.Service;
import java.awt.Image;
import com.TripMap.mapper.Usermapper;
import com.TripMap.pojo.User;
import com.TripMap.utils.HttpUtils;
import com.alibaba.fastjson.JSONObject;
import com.TripMap.utils.SaveImageUtil;
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
    /**
     * @function 微信登录
     * @param appid 小程序 appId
     * @param secret 小程序 appSecret
     * @param code 微信登录 code
     * @return 用户对象
     * @return JSONObject,包含openid,session_key
     * @throws Exception
     */
    public JSONObject Wxlogin(String appid,String secret,String code) throws Exception{
        String url="https://api.weixin.qq.com/sns/jscode2session";
        String params="appid="+appid+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code";
        HttpResponse<String> data=new HttpUtils().sendGet(url, params);
        JSONObject json=JSONObject.parseObject(data.body());
        return json;
    }


}
