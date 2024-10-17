package com.mongodbObject;

import org.bson.Document;

public class UserDocument extends Document {
    UserDocument(User user){
        //Document doc=new Document();
        super.append("name", user.getName()).append("password", user.getPassword())
        .append("age", user.getAge()).append("Email", user.getEmail());
    }
}
