package com.TripMap.pojo;

import java.time.LocalDateTime;

import org.bson.Document;

import com.TripMap.mapper.Usermapper;

import lombok.Data;
@Data
public class reply {
    String name;//回复的用户名
    String avatarUrl;//头像url
    String content;//回复内容
    LocalDateTime replyTime;//回复时间
    String uuid;//用户唯一标识符
    public reply() {
        name = "";
        avatarUrl = "";
        content = "";
        replyTime = null;
        uuid = "";
    }

    public reply(String name, String avatarUrl, String content) {
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.content = content;
        replyTime = LocalDateTime.now();
        this.uuid = "";
    }
    /**
     * @function 用用户id和content来创建回复
     * @param uuid 用户ID
     * @param content 回复内容
     * @throws Exception
     */
    public reply(String uuid,String content) throws Exception{
        this.content=content;
        this.uuid = uuid;
        replyTime=LocalDateTime.now();
        Usermapper mapper=new Usermapper();
        User user=mapper.getUserByUUID(uuid);
        this.name=user.getName();
        this.avatarUrl=user.getAvatarUrl();
    }
    public reply(Document doc){
        this.name=doc.getString("name");
        this.avatarUrl=doc.getString("avatarUrl");
        this.content=doc.getString("content");
        this.replyTime=LocalDateTime.parse(doc.getString("replyTime"));
        this.uuid = doc.getString("uuid");
    }
}
