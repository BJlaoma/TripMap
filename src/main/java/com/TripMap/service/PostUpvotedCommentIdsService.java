package com.TripMap.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.TripMap.mapper.UpvotedCommentIdsmapper;
import java.util.ArrayList;
import java.util.UUID;
import com.TripMap.pojo.UpvotedCommentIds;



public class PostUpvotedCommentIdsService
{

    private  UpvotedCommentIdsmapper map;

    public String post(String uuid,ArrayList<String>array)
    {
        if( map.findByUuid(uuid))
        {
           if( map.updataByUuid(uuid,array))
               return "更新成功";
           else
               return "更新失败";

         }
       else
       {
           if(map.addUpvotedCommentIds(uuid,array))
               return "更新成功";
           else
               return "更新失败";
        }
    }

}
