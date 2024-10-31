
package com.TripMap.pojo;

import org.bson.Document;

public class UserDocument extends Document {
    public UserDocument(User user){
        super.append("uuid",user.getUuid().toString()).
        append("name", user.getName()).
        append("password", user.getPassword()).
        append("avatarUrl", user.getAvatarUrl()).
        append("createdAt", user.getCreatedAt().toString()).
        append("updatedAt", user.getUpdatedAt().toString());
    }
    public UserDocument(String key,User user){
        Document doc=new Document();
        doc.append("uuid",user.getUuid().toString()).
        append("name", user.getName()).
        append("password", user.getPassword()).
        append("avatarUrl", user.getAvatarUrl()).
        append("createdAt", user.getCreatedAt().toString()).
        append("updatedAt", user.getUpdatedAt().toString());
        super.append(key,doc);
    }
}
