package com.TripMap.mapper;

import com.TripMap.pojo.User;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class Usermapper {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 查找是否有此用户
     * @param name 用户姓名
     * @return boolean
     */
    public boolean foundUser(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        return mongoTemplate.count(query, User.class) > 0;
    }

    /**
     * 通过用户名和密码来获取用户的所有信息
     * @param name 用户名
     * @param password 密码
     * @return User
     * @throws Exception 如果用户不存在
     */
    public User foundUser(String name, String password) throws Exception {
        Query query = new Query(Criteria.where("name").is(name).and("password").is(password));
        User user = mongoTemplate.findOne(query, User.class);
        if (user == null) {
            throw new Exception("登录失败，账号或者密码错误");
        }
        return user;
    }

    /**
     * 根据用户名和密码查找用户
     * @param name 用户名
     * @param password 密码
     * @return User
     */
    public User findUserByNameAndPassword(String name, String password) {
        Query query = new Query(Criteria.where("name").is(name).and("password").is(password));
        return mongoTemplate.findOne(query, User.class);
    }

    /**
     * 添加用户
     * @param user 用户对象
     */
    public void addUser(User user) {
        mongoTemplate.insert(user, "user");
    }
}
