package com.TripMap.controller;
import java.util.ArrayList;
import org.json.JSONObject;
import org.json.JSONArray;
import com.TripMap.pojo.*;
import com.TripMap.service.PostUpvotedCommentIdsService;
import com.TripMap.service.SignupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.TripMap.mapper.UpvotedCommentIdsmapper;

import com.TripMap.pojo.UpvotedCommentIds;

@RestController
@RequestMapping("/user/{userId}/upvotedCommentIds")
public class UpvotedCommentIdsController {


    private   UpvotedCommentIdsmapper map;

    private   PostUpvotedCommentIdsService  p;

    @GetMapping()
    public JsonResult<ArrayList<String>> getListByUuid(@RequestParam("userId") String uuid) throws Exception{

        ArrayList<String>upCommentIds=map.getByUuid(uuid).getCommentIds();
        return new JsonResult<ArrayList<String>>(upCommentIds);
    }




    @PostMapping()
    public JsonResult<String> postList(@RequestParam("userId") String uuid,@RequestBody JSONArray data) throws Exception{
        ArrayList<String>array=new ArrayList<>();
        for (int i = 0; i <data.length(); i++) {
            array.add(data.getString(i));
        }
        p.post(uuid,array);
        return new JsonResult<String>(p.post(uuid,array));
    }


}
