package com.TripMap.mapper;

import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.model.Filters;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.TripMap.pojo.User;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

public class Usermapper extends mapper {
    MongoCollection<User> collection;

    public Usermapper(){
        super();
        collection=super.getDatabase().getCollection("user",User.class);
    }

    /**
     * @param name 用户姓名
     * @function 查找是否有此user
     * @return boolean
     * @auther wzb
     */
    public boolean foundUser(String name){
        Bson filter=Filters.eq("name",name);
        FindIterable<User> doc=collection.find(filter);
        if(doc==null){
                return false;
        }else{
            return true;
        }
    }

    /**
     * @function 通过用户密码来获取用户的所有信息
     * @return User
     * @author wzb
     */
    public User foundUser(String name,String password) throws Exception{
        Bson filter=Filters.and(Filters.eq("name",name),Filters.eq("password",password));
        User doc=collection.find(filter,User.class).first();
        if(doc==null){
            throw new Exception("登录失败，账号或者密码错误");
        }
        return doc;
    }

    public void addUser(User user){
        collection.insertOne(user);
    }
}
