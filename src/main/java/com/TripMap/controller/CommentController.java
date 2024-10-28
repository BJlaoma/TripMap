/*
 * @Author: WZB 150590206+BJlaoma@users.noreply.github.com
 * @Date: 2024-10-28 00:59:56
 * @LastEditors: WZB 150590206+BJlaoma@users.noreply.github.com
 * @LastEditTime: 2024-10-28 13:25:11
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

@RestController
@RequestMapping("/comment")
public class CommentController {
    
    @RequestMapping("/getCommentsByScenicId")
    public JsonlistResult<comment> getCommentsByScenicId(@RequestParam("scenicId") String scenicId) throws Exception{
        CommentService commentService = new CommentService();
        return new JsonlistResult<comment>(commentService.getCommentsByScenicId(scenicId));
    }   
    @RequestMapping("/addComment")
    public JsonResult<comment> addComment(@RequestBody JSONObject data) throws Exception{
        CommentService commentService = new CommentService();
        return new JsonResult<comment>(commentService.addComment(data));
    }
}
