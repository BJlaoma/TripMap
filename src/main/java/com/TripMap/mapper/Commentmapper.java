package com.TripMap.mapper;

import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.model.Filters;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.TripMap.pojo.comment;
import com.TripMap.pojo.reply;
import com.mongodb.client.model.Updates;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Commentmapper extends mapper {
    MongoCollection<Document> collection;

    public Commentmapper() {
        super();
        collection = super.getDatabase().getCollection("comment");
    }

    /**
     * @param commentID 评论ID
     * @function 查找是否有此评论
     * @return boolean
     */
    public boolean foundComment(String commentID) {
        Bson filter = Filters.eq("commentID", commentID);
        FindIterable<Document> doc = collection.find(filter);
        return doc.iterator().hasNext();
    }

    /**
     * @function 通过评论ID获取评论的所有信息
     * @return comment
     */
    public comment getCommentById(String commentID) throws Exception {
        Bson filter = Filters.eq("commentID", commentID);
        Document doc = collection.find(filter).first();

        if (doc == null) {
            throw new Exception("未找到该评论");
        }
        return new comment(doc);
    }

    /**
     * @function 添加新评论
     */
    public void addComment(comment comment) throws Exception{
        collection.insertOne(new Document()
                .append("like", comment.getLike())
                .append("commentID", comment.getCommentID())
                .append("content", comment.getContent())
                .append("commentTime", comment.getCommentTime().toString())
                .append("uuid", comment.getUuid().toString())
                .append("scenic_ID", comment.getScenic_ID())
                .append("avatarUrl", comment.getAvatarUrl())
                .append("name", comment.getName())
                .append("rating", comment.getRating())
                .append("replies", new ArrayList<reply>()));
    }

    /**
     * @function 获取特定景点的所有评论
     * @param scenicId 景点ID
     * @return List<comment>
     */
    public ArrayList<comment> getCommentsByScenicId(String scenicId) {
        Bson filter = Filters.eq("scenic_ID", scenicId);
        FindIterable<Document> docs = collection.find(filter);
        ArrayList<comment> comments = new ArrayList<>();
        for (Document doc : docs) {
            comments.add(new comment(doc));
        }
        return comments;
    }


    /**
     * @function 更新评论的点赞数
     */
    public void updateLike(String commentID) {
        Bson filter = Filters.eq("commentID", commentID);
        Bson update = new Document("$inc", new Document("like", 1));
        collection.updateOne(filter, update);
    }
    /**
     * @function 在评论下面添加回复
     * @param commentID 评论ID
     * @param reply 回复对象
     * @throws Exception
     */
    public void addReply(String commentID, reply reply) throws Exception {
        // 创建filter
        Bson filter = Filters.eq("commentID", commentID);
        
        // 创建update文档
        Document replyDoc = new Document()
            .append("name", reply.getName())
            .append("avatarUrl", reply.getAvatarUrl())
            .append("content", reply.getContent())
            .append("replyTime", reply.getReplyTime().toString())
            .append("uuid", reply.getUuid());
        
        // 使用$addToSet操作符将reply添加到replies数组
        Bson update = new Document("$addToSet", new Document("replies", replyDoc));
        
        // 执行更新操作
        collection.updateOne(filter, update);
    }

    public static void main(String[] args) throws Exception {
        Commentmapper mapper = new Commentmapper();
        comment testComment = new comment("testID", "这是一条测试评论", UUID.randomUUID(), "scenic001");
        mapper.addComment(testComment);
        System.out.println("添加评论成功");
        
        System.out.println("查找评论: " + mapper.foundComment("testID"));
        comment retrievedComment = mapper.getCommentById("testID");
        System.out.println("获取的评论内容: " + retrievedComment.getContent());
        
        mapper.updateLike("testID");
        retrievedComment = mapper.getCommentById("testID");
        System.out.println("更新后的点赞数: " + retrievedComment.getLike());
        
        List<comment> scenicComments = mapper.getCommentsByScenicId("scenic001");
        System.out.println("景点评论数量: " + scenicComments.size());
    }
}
