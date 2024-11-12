package com.TripMap.mapper;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

import lombok.Data;
@Data
public class mapper {
    static String mongoURL="mongodb://user:123456@localhost:27017/?authSource=journeymap";
    protected MongoDatabase database;
    protected MongoClient mongoClient;
    public mapper(){
        mongoClient = MongoClients.create(mongoURL);
        database = mongoClient.getDatabase("journeymap");
        System.out.println("Connection successful to database: " + database.getName());
    }
    public void close(){
        mongoClient.close();
    }
}
