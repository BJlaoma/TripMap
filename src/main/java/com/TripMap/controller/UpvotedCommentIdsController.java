package com.TripMap.controller;
import java.util.ArrayList;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.TripMap.pojo.*;
import com.TripMap.service.PostUpvotedCommentIdsService;
import com.TripMap.service.SignupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.TripMap.mapper.UpvotedCommentIdsmapper;

@RestController
@RequestMapping("/user/upvotedCommentIds")
public class UpvotedCommentIdsController {


    private   UpvotedCommentIdsmapper map;

    private   PostUpvotedCommentIdsService  p;

    @GetMapping()
    public JsonlistResult<String> getListByUuid(@RequestParam("userId") String uuid) throws Exception{
        map=new UpvotedCommentIdsmapper();
        ArrayList<String>upCommentIds=map.getByUuid(uuid).getCommentIds();
        return new JsonlistResult<String>(upCommentIds);
    }




    @PostMapping()
    public JsonResult<String> postList(@RequestParam("userId") String uuid,@RequestBody JSONObject json) throws Exception{
        ArrayList<String>array=new ArrayList<>();
        
        JSONArray data=json.getJSONArray("data");
        for (int i = 0; i <data.size(); i++) {
            array.add(data.getString(i));
        }
        p=new PostUpvotedCommentIdsService();
        return new JsonResult<String>(p.post(uuid,array));
    }


}
