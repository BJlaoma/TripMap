
package com.TripMap.service;

import org.springframework.stereotype.Service;
import java.util.UUID;

import com.TripMap.mapper.Usermapper;
import com.TripMap.mapper.Favorsmapper;
import com.TripMap.pojo.User;
import com.TripMap.pojo.Userfavors;
//注册
@Service
public class SignupService {
    public SignupService(){}
    /**
     * @function 注册
     * @param name 用户名
     * @param password 密码
     * @param avatarUrl 头像url
     * @return 用户对象
     * @throws Exception 
     */
    public User Signup(String name, String password, String avatarUrl,String uuid) throws Exception {
        Usermapper mapper = new Usermapper();
        Favorsmapper favorsmapper=new Favorsmapper();
        User user;
        
        try {
            // 尝试通过openid(uuid)找到用户
            user = mapper.foundUserByOpenid(uuid);
            return user;
        } catch (Exception e) {
            if (e.getMessage().equals("用户不存在")) {
                // 如果用户不存在，创建新用户
                user = new User(uuid);
                Userfavors userfavors=new Userfavors(user.getUuid());
                if (avatarUrl != null && !avatarUrl.isEmpty()) {
                    user.setAvatarUrl(avatarUrl);
                }
                mapper.addUser(user);
                favorsmapper.insertUserfavors(userfavors);
            } else {
                // 如果是其他异常，继续抛出
                throw e;
            }
        } finally {
            mapper.close();
        }
        
        return user;
    }
}
