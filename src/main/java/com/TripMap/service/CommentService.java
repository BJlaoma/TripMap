/*
 * @Author: WZB 150590206+BJlaoma@users.noreply.github.com
 * @Date: 2024-10-28 00:56:03
 * @LastEditors: WZB 150590206+BJlaoma@users.noreply.github.com
 * @LastEditTime: 2024-10-29 17:01:53
 * @FilePath: \TripMap\src\main\java\com\TripMap\service\CommentService.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
package com.TripMap.service;

import com.TripMap.mapper.Commentmapper;
import com.TripMap.pojo.comment;
import com.TripMap.pojo.reply;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.UUID;

public class CommentService {
    private Commentmapper commentmapper;

    public CommentService(){
        commentmapper=new Commentmapper();
    }   

    /**
     * @function 获取特定景点的所有评论
     * @param scenicId 景点ID
     * @return List<comment>
     */
    public ArrayList<comment> getCommentsByScenicId(String scenicId) {
        return commentmapper.getCommentsByScenicId(scenicId);
    }
    /**
     * @function 添加评论，直接传入comment对象
     * @param comment
     
     */
    public void addComment(comment comment) throws Exception {
        commentmapper.addComment(comment);
    }   
    /**
     * @function 添加评论,弃用
     * @param data json对象，包含comment对象
     * @throws Exception
     */
    public comment addComment(JSONObject data) throws Exception {
        comment comment=new comment(data.getString("uuid"),data.getString("content"),UUID.fromString(data.getString("uuid")),data.getString("scenic_ID"));
        commentmapper.addComment(comment);  
        return comment;
    }   
    /**
     * @function 添加评论,用用户来添加
     * @param data json对象，包含uuid,content,scenic_ID和rating
     * @throws Exception
     */
    public comment addCommentByUser(JSONObject data) throws Exception {
        comment comment=new comment(data.getString("uuid"),data.getString("content"),data.getString("scenic_ID"),data.getString("rating"));
        commentmapper.addComment(comment);  
        return comment;
    }
    /**
     * @function 更新评论的点赞数
     * @param commentID 评论ID
     */
    public void updateLike(String commentID) throws Exception {  
        commentmapper.updateLike(commentID);
    }
    /**
     * @function 在评论下面添加回复
     * @param commentID 评论ID
     * @param data json对象，包含uuid和content
     * @throws Exception
     */
    public reply addReply(String commentID,JSONObject data) throws Exception {
        reply _reply=new reply(data.getString("uuid"),data.getString("content"));
        commentmapper.addReply(commentID,_reply);
        return _reply;
    }

    public static void main(String[] args) {
        CommentService commentService = new CommentService();
        System.out.println(commentService.getCommentsByScenicId("1"));
    }   
}
