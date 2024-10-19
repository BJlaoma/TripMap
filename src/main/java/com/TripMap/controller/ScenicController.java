package com.TripMap.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TripMap.mapper.Scenicmapper;
import com.TripMap.pojo.JsonResult;
import com.TripMap.pojo.JsonlistResult;
import com.TripMap.pojo.Scenic;
import com.alibaba.fastjson.JSONObject;

@RestController
@RequestMapping("/scenic")
public class ScenicController {
    
    /**
     * @param data json对象，如果是内置message和label,就是模糊查找；只有name就是精确查找
     * @function 精确查找
     * @throws Exception 
     * 
     */
    @RequestMapping("/foundscenic")
    public JsonResult<Scenic> foundscenic(@RequestBody JSONObject data) throws Exception{
        Scenicmapper mapper=new Scenicmapper();
        return new JsonResult<Scenic>(mapper.foundScenic(data.getString("name")));
    }
    @RequestMapping("/foundscenics")
    public JsonlistResult<Scenic> foundscenics(@RequestBody JSONObject data) throws Exception{
        Scenicmapper mapper=new Scenicmapper();
        return new JsonlistResult<Scenic>(mapper.foundScenics(data.getString("message"),data.getString("label")));
    }

    @RequestMapping("/datatest")
    public String datatest(@RequestBody JSONObject data ){
        System.out.println(data.getString("name"));
        return "succees";
    }
}
