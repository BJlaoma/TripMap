package com.mongodbObject;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBInsertExample {
    public static void main(String[] args) {
        // 连接到 MongoDB
        String uri = "mongodb://localhost:27017";
        try (com.mongodb.client.MongoClient mongoClient = MongoClients.create(uri)) {
            // 选择数据库
            MongoDatabase database = mongoClient.getDatabase("yourDatabaseName");
            // 选择集合
            MongoCollection<Document> collection = database.getCollection("testUser");

            // 创建 User 对象
            User user = new User("Alice", "password123", 30, "alice@example.com");

            // 插入 User 对象
            collection.insertOne(new UserDocument(user));
            System.out.println("用户插入成功: " + user.getName());
        }
    }
}
