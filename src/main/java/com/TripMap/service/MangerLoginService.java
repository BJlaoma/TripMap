package com.TripMap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.TripMap.mapper.UserMapper; // 假设有一个 UserMapper 接口用于数据库操作
import com.TripMap.pojo.User; // 假设有一个 User 类表示用户

@Service
public class ManagerLoginService {

    // 自动注入 UserMapper
    @Autowired
    private UserMapper userMapper;

    /**
     * 管理员登录
     * @param name 管理员用户名
     * @param password 管理员密码
     * @return 登录成功的管理员用户对象，如果登录失败则返回 null
     */
    public User login(String name, String password) {
        // 使用 UserMapper 来检查用户名和密码是否正确
        User user = userMapper.findUserByNameAndPassword(name, password);
        if (user != null && user.isManager()) {
            // 如果用户是管理员，返回用户对象
            return user;
        } else {
            // 如果用户不是管理员或密码错误，返回 null
            return null;
        }
    }
}
