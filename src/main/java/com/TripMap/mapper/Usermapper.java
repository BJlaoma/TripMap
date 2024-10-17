package com.TripMap.mapper;

import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.model.Filters;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.TripMap.pojo.User;
import com.TripMap.pojo.UserDocument;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

public class Usermapper extends mapper {
    MongoCollection<Document> collection;

    public Usermapper(){
        super();
        collection=super.getDatabase().getCollection("user");
    }

    /**
     * @param name 用户姓名
     * @function 查找是否有此user
     * @return boolean
     * @auther wzb
     */
    public boolean foundUser(String name){
        Bson filter=Filters.eq("name",name);
        FindIterable<Document> doc=collection.find(filter);
        return doc.iterator().hasNext();//如果有下一个文档，返回 true；否则返回 false
    }

    /**
     * @function 通过用户密码来获取用户的所有信息
     * @return User
     * @author wzb
     */
    public User foundUser(String name,String password) throws Exception{
        Bson filter=Filters.and(Filters.eq("name",name),Filters.eq("password",password));
        Document doc=collection.find(filter).first();

        if(doc==null){
            throw new Exception("登录失败，账号或者密码错误");
        }
        return new User(doc);
    }

    public void addUser(User user){
        collection.insertOne(new UserDocument(user));
    }

    public static void main(String[] args) throws Exception {
        User user=new User("2", "2");
        Usermapper map=new Usermapper();
    //    map.addUser(user);
        System.out.println("添加成功");
        System.out.println(map.foundUser("2"));
        System.out.println(map.foundUser("2", "2").toString());
    }
}
