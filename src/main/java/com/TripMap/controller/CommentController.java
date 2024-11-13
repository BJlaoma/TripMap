/*
 * @Author: WZB 150590206+BJlaoma@users.noreply.github.com
 * @Date: 2024-10-28 00:59:56
 * @LastEditors: WZB 150590206+BJlaoma@users.noreply.github.com
 * @LastEditTime: 2024-10-29 18:57:02
 * @FilePath: \TripMap\src\main\java\com\TripMap\controller\CommentController.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
package com.TripMap.controller;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import com.TripMap.pojo.JsonResult;
import com.TripMap.pojo.JsonlistResult;
import com.TripMap.service.CommentService;
import com.alibaba.fastjson.JSONObject;
import com.TripMap.pojo.comment;
import com.TripMap.pojo.reply;

@RestController
@RequestMapping("/comment")
public class CommentController {
    /**
     * @function 获取特定景点的所有评论
     * @param scenicId 景点ID
     * @return comment对象列表
     * @throws Exception
     */
    @RequestMapping("/getCommentsByScenicId")
    public JsonlistResult<comment> getCommentsByScenicId(@RequestParam("scenicId") String scenicId) throws Exception{
        CommentService commentService = new CommentService();
        return new JsonlistResult<comment>(commentService.getCommentsByScenicId(scenicId));
    }   
    /**
     * @function 添加评论,用户评论
     * @param data json对象，包含uuid,content,scenic_ID和rating
     * @return comment对象
     * @throws Exception
     */
    @RequestMapping("/addComment")
    public JsonResult<comment> addComment(@RequestBody JSONObject data) throws Exception{
        CommentService commentService = new CommentService();
        return new JsonResult<comment>(commentService.addCommentByUser(data));
    }

    /**
     * @function 在评论下面添加回复
     * @param data json对象，包含commentID,uuid,content,uuid是指发送回复的用户id
     * @throws Exception
     */
    @RequestMapping("/addReply")
    public JsonResult<reply> addReply(@RequestBody JSONObject data) throws Exception{
        CommentService commentService = new CommentService();
        return new JsonResult<reply>(commentService.addReply(data.getString("commentID"),data));
    }
}
