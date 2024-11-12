package com.TripMap.mapper;


import com.TripMap.pojo.User;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.model.Filters;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.TripMap.pojo.UpvotedCommentIds;

import java.util.ArrayList;
import java.util.Arrays;


public class UpvotedCommentIdsmapper extends mapper
{
    MongoCollection<Document> collection;

    public UpvotedCommentIdsmapper()
    {
        super();
        collection = super.getDatabase().getCollection("upCommentIds");
    }

    /**
     * @param upvotedCommentIds 储存id列表的对象
     * @function 新增用户点赞评论的id列表
     * @auther liangxin
     */
    public boolean addUpvotedCommentIds(UpvotedCommentIds upvotedCommentIds)
    {
        InsertOneResult result = collection.insertOne(new Document("uuid",upvotedCommentIds.getUuid()).append("commentIds",upvotedCommentIds.getCommentIds()));
        return (result.wasAcknowledged());
    }

    /**
     * @param uuid 和数组
     * @function 新增用户点赞评论的id列表
     * @auther liangxin
     */
    public boolean addUpvotedCommentIds(String uuid,ArrayList<String>array)
    {
        if(findByUuid(uuid))
        {
            return false;
        }
        InsertOneResult result = collection.insertOne(new Document("uuid",uuid).append("commentIds",array));
        return (result.wasAcknowledged());
    }



    /**
     * @param uuid 用户标识符
     * @function 删除id列表
     * @auther liangxin
     */
    public void deleteUpvotedCommentIds(String uuid)
    {
        collection.deleteOne(new Document("uuid", uuid));
    }

    /**
     * @param uuid 用户标识符
     * @function 获取该用户的点赞评论id列表
     * @return UpvotedCommentIds对象
     * @auther liangxin
     */
    public UpvotedCommentIds getByUuid(String uuid)
    {
        Bson filter=Filters.eq("uuid",uuid);
        Document doc=collection.find(filter).first();
        return new UpvotedCommentIds(doc);
    }

    /**
     * @param uuid 用户标识符
     * @function 查找是否有此user的点赞评论id列表
     * @return boolean
     * @auther liangxin
     */
    public boolean  findByUuid(String uuid)
    {
        Bson filter=Filters.eq("uuid",uuid);
        Document doc= collection.find(filter).first();
        return doc!=null;
    }


    /**
     * @param uuid ArrayList<String> 用户标识符和id数组
     * @function 更新点赞id列表
     * @return boolean
     * @auther liangxin
     */
    public boolean  updataByUuid(String uuid, ArrayList<String>array)
    {
        UpdateResult result = collection.updateOne( new Document("uuid",uuid), new Document("$set", new Document("commentIds", array)));
        return (result.getModifiedCount() > 0);
    }



    public static void main(String[] args) throws Exception
    {
        ArrayList<String>array=new ArrayList<>();
        array.add("不好吃");

        UpvotedCommentIds upvotedCommentIds=new UpvotedCommentIds("114514",array);
        UpvotedCommentIdsmapper map=new UpvotedCommentIdsmapper();
        //map.addUpvotedCommentIds(upvotedCommentIds);
        map.updataByUuid(upvotedCommentIds.getUuid(),upvotedCommentIds.getCommentIds());



        System.out.println(array);
    }





}


