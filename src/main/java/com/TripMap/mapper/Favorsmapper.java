package com.TripMap.mapper;

import java.util.List;

import org.bson.Document;

import com.TripMap.pojo.Userfavors;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.FindIterable;
import org.bson.conversions.Bson;

public class Favorsmapper extends mapper{
    MongoCollection<Document> collection;
    public Favorsmapper(){
        super();
        collection=super.getDatabase().getCollection("userfavors");
    }
    public Userfavors getUserfavors(String uuid) throws Exception{
        Bson filter=Filters.eq("uuid",uuid);
        FindIterable<Document> doc=collection.find(filter);
        return new Userfavors(doc.first());
    }
    public void updateUserfavors(String uuid,Userfavors userfavors) throws Exception{
        Bson filter=Filters.eq("uuid",uuid);
        collection.replaceOne(filter,userfavors.toDocument());
    }
    public void updateScenicID(String uuid,String id) throws Exception{
        Bson filter=Filters.eq("uuid",uuid);
        collection.updateOne(filter,Updates.push("id",id));
    }
    public void insertUserfavors(Userfavors userfavors) throws Exception{
        collection.insertOne(userfavors.toDocument());
    }
    public void insertScenicID(String uuid,String id) throws Exception{
        Bson filter=Filters.eq("uuid",uuid);
        collection.updateOne(filter,Updates.push("id",id));
    }
    public void deleteUserfavors(String uuid) throws Exception{
        Bson filter=Filters.eq("uuid",uuid);
        collection.deleteOne(filter);
    }
    public void deleteScenicID(String uuid,String id) throws Exception{
        Bson filter=Filters.eq("uuid",uuid);
        collection.updateOne(filter,Updates.pull("id",id));
    }
    public boolean isExist(String uuid,String id) throws Exception{
        Bson filter = Filters.eq("uuid", uuid);
        FindIterable<Document> result = collection.find(filter);
        Document doc = result.first();
        
        if (doc == null) {
            return false;
        }
        
        @SuppressWarnings("unchecked")
        List<String> ids = (List<String>) doc.get("id");
        return ids != null && ids.contains(id);
    }
}
