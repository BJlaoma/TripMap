package com.TripMap.pojo;
import java.time.LocalDate;
import java.util.UUID;
import java.util.ArrayList;
import org.bson.Document;

import lombok.Data;

@Data
public class UpvotedCommentIds {
   private String uuid;//用户唯一标识符
   private ArrayList<String> CommentIds;//用户点赞评论的id数组


    public UpvotedCommentIds(){}//数据库要求有默认构造函数
    public UpvotedCommentIds(String uuid,ArrayList<String> arry){

        this.uuid=uuid;
        this.CommentIds=new ArrayList<>();
        for(String commentId:arry){
            this.CommentIds.add(commentId);
        }

    }
    public UpvotedCommentIds(Document doc){
        this.uuid=doc.getString("uuid");
        this.CommentIds=new ArrayList<>();
        if(doc.get("commentIds")!=null){ 
            for(String commentId:doc.getList("commentIds",String.class)){
                this.CommentIds.add(commentId);
            }
        }
    }
}
