/*
 * @Author: WZB 150590206+BJlaoma@users.noreply.github.com
 * @Date: 2024-10-16 22:24:31
 * @LastEditors: WZB 150590206+BJlaoma@users.noreply.github.com
 * @LastEditTime: 2024-10-31 17:15:16
 * @FilePath: \TripMap\src\main\java\com\TripMap\mapper\Usermapper.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
package com.TripMap.mapper;

import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.model.Filters;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.TripMap.pojo.User;
import com.TripMap.pojo.UserDocument;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import java.time.LocalDate;

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

    /**
     * @function 添加用户
     * @param user 用户对象
     * @author wzb
     */
    public void addUser(User user){
        collection.insertOne(new UserDocument(user));
    }

    public User foundUserByOpenid(String openid) throws Exception{
        Bson filter=Filters.eq("password",openid);
        Document doc=collection.find(filter).first();
        if(doc==null){
            throw new Exception("用户不存在");
        }
        return new User(doc);
    }
    /**
     * @function 更新用户,弃用
     * @param user 用户对象
     * @author wzb
     */
    public void updateUser(User user) throws Exception{
        Bson filter=Filters.eq("uuid",user.getUuid());
        collection.updateOne(filter, new UserDocument("$set",user));
        System.out.println("更新成功");
    }

    public void updateAvatarUrl(String uuid,String avatarUrl) throws Exception{
        Bson filter=Filters.eq("uuid",uuid);
        collection.updateOne(filter, new Document("$set",new Document("avatarUrl",avatarUrl).append("updatedAt",LocalDate.now().toString())));
    }

    public void updateName(String uuid,String name) throws Exception{
        Bson filter=Filters.eq("uuid",uuid);
        collection.updateOne(filter, new Document("$set",new Document("name",name).append("updatedAt",LocalDate.now().toString())));
    }
    public static void main(String[] args) throws Exception {
        User user=new User("2", "2");
        Usermapper map=new Usermapper();
    //    map.addUser(user);
        System.out.println("添加成功");
        System.out.println(map.foundUser("2"));
        System.out.println(map.foundUser("2", "2").toString());
    }

    public User getUserByUUID(String uuid) {
        Bson filter=Filters.eq("uuid",uuid);
        Document doc=collection.find(filter).first();
        return new User(doc);
    }

}
