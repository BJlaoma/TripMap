
package com.TripMap.pojo;
import java.time.LocalDateTime;
import java.util.UUID;
import org.bson.Document;
import com.TripMap.mapper.Commentmapper;
import com.TripMap.mapper.Usermapper;

import lombok.Data;

import java.util.ArrayList;
@Data
public class comment {
    private Integer like;//点赞数
    private String commentID;//评论序号
    private String content;    //评论的内容
    private LocalDateTime commentTime;
    private UUID uuid;//用户唯一标识符
    private String scenic_ID;
    private String avatarUrl; //头像url
    private String name; //用户名
    private String rating; //评分
    private ArrayList<reply> replies; //回复列表
    public comment() {
        like = 0;
        commentID = "";
        content = "";
        commentTime = null;
        scenic_ID = "";
        uuid = null;
        avatarUrl = "";
        name = "";
        rating = "";
        replies = new ArrayList<>();
    }

    /**
     * @function 添加评论,弃用，类属性修改了
     * @param commentID 评论的用户名
     * @param content 评论的内容
     * @param uuid 用户唯一标识符
     * @param scenic_ID 景点ID
     */
    public comment(String commentID, String content, UUID uuid, String scenic_ID) {   //comment的内容如何传入看一下后面要不要改
        this.like = 0;
        this.commentID = commentID;
        this.content = content;
        this.commentTime = LocalDateTime.now();
        this.uuid = uuid;
        this.scenic_ID = scenic_ID;
        this.avatarUrl = "";
        this.name = "";
        this.rating = "";
        this.replies = new ArrayList<>();
    }

    // 添加新的构造函数
    public comment(Document doc) {
        this.like = doc.getInteger("like");
        this.commentID = doc.getString("commentID");
        this.content = doc.getString("content");
        this.commentTime = LocalDateTime.parse(doc.getString("commentTime"));
        this.uuid = UUID.fromString(doc.getString("uuid"));
        this.scenic_ID = doc.getString("scenic_ID");
        this.avatarUrl = doc.getString("avatarUrl");
        this.name = doc.getString("name");
        this.rating = doc.getString("rating");
        this.replies = new ArrayList<>();
        for(Document replyDoc : doc.getList("replies", Document.class)){
            this.replies.add(new reply(replyDoc));
        }
    }

    /**
     * @function 用用户id和content来创建评论
     * @param uuid 用户ID
     * @param content 评论内容
     * @param scenic_ID 景点ID
     * @param rating 评分
     */
    public comment(String uuid,String content,String scenic_ID,String rating){
        this.uuid = UUID.fromString(uuid);
        this.content = content;
        this.scenic_ID = scenic_ID;
        this.rating = rating;
        this.commentTime = LocalDateTime.now();
        this.like = 0;
        Usermapper mapper=new Usermapper();
        User user=mapper.getUserByUUID(uuid);
        this.commentID = UUID.randomUUID().toString();
        this.avatarUrl = user.getAvatarUrl();
        this.name = user.getName();
        this.replies = new ArrayList<>();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getScenic_ID() {
        return scenic_ID;
    }

    public void setScenic_ID(String scenic_ID) {
        this.scenic_ID = scenic_ID;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }     //（说实话感觉这个函数没什么用）

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(LocalDateTime commentTime) {
        this.commentTime = commentTime;
    }


    public void increaseLike() {                   //点赞增加
        this.like++;
    }
    public void addReply(reply reply) {
        this.replies.add(reply);
    }
};
