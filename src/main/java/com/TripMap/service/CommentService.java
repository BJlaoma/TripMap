/*
 * @Author: WZB 150590206+BJlaoma@users.noreply.github.com
 * @Date: 2024-10-28 00:56:03
 * @LastEditors: WZB 150590206+BJlaoma@users.noreply.github.com
 * @LastEditTime: 2024-10-28 01:09:45
 * @FilePath: \TripMap\src\main\java\com\TripMap\service\CommentService.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
package com.TripMap.service;

import com.TripMap.mapper.Commentmapper;
import com.TripMap.pojo.comment;

import java.util.ArrayList;

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
     * @function 添加评论   
     * @param comment
     
     */
    public void addComment(comment comment) throws Exception {
        commentmapper.addComment(comment);
    }   

    /**
     * @function 更新评论的点赞数
     * @param commentID 评论ID
     */
    public void updateLike(String commentID) throws Exception {  
        commentmapper.updateLike(commentID);
    }

    public static void main(String[] args) {
        CommentService commentService = new CommentService();
        System.out.println(commentService.getCommentsByScenicId("1"));
    }   
}
