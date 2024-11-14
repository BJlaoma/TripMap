package com.TripMap.controller;

import com.TripMap.pojo.JsonlistResult;
import com.TripMap.pojo.Tag;
import com.TripMap.service.TagInforGetService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/scenic")
public class TagController {

    @Autowired
    private TagInforGetService tagService;


    @GetMapping("/taglist")
    public JSONArray getTags() {
        JSONArray json=new JSONArray();
        for(Tag tag:Tag.values()){
            json.add(tag.toString());
        }
        return json;
    }
    /**
     * 获取多个标签的信息
     * @param tags 标签名
     * @return 返回标签的信息
     */
    @PostMapping("/tags")
    public JSONObject getTag(@RequestBody JSONObject data){
        JSONObject json=new JSONObject();
        JSONArray array=data.getJSONArray("tags");
        for(int i=0;i<array.size();i++){
            json.put(array.getString(i),Tag.fromLabel(array.getString(i)));
        }
        return json;
    }

}