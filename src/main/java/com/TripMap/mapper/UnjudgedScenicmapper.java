package com.TripMap.mapper;

import org.bson.Document;
import com.mongodb.client.MongoCollection;
/*
 * 类未使用
 */
public class UnjudgedScenicmapper extends mapper{
    MongoCollection<Document> collection;

    public UnjudgedScenicmapper(){
        super();
        collection=super.getDatabase().getCollection("unjudgedScenic");
    }

}
