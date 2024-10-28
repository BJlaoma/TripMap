
package com.TripMap.pojo;
import java.time.LocalDateTime;
import java.util.UUID;
import org.bson.Document;

public class comment {
    private Integer like;
    private String commentID;//评论的用户名
    private String content;    //评论的内容
    private LocalDateTime commentTime;
    private UUID uuid;//用户唯一标识符
    private String scenic_ID;


    public comment() {
        like = 0;
        commentID = "";
        content = "";
        commentTime = null;
        scenic_ID = "";
        uuid = null;
    }

    /**
     * @function 添加评论
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
    }

    // 添加新的构造函数
    public comment(Document doc) {
        this.like = doc.getInteger("like");
        this.commentID = doc.getString("commentID");
        this.content = doc.getString("content");
        this.commentTime = LocalDateTime.parse(doc.getString("commentTime"));
        this.uuid = UUID.fromString(doc.getString("uuid"));
        this.scenic_ID = doc.getString("scenic_ID");
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
}
