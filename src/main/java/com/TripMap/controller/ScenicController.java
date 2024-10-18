package com.TripMap.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TripMap.mapper.Scenicmapper;
import com.TripMap.pojo.Scenic;
import com.alibaba.fastjson.JSONObject;

@RestController
@RequestMapping("/Scenic")
public class ScenicController {
    
    /**
     * @param data json对象，如果是内置message和label,就是模糊查找；只有name就是精确查找
     * @function 精确查找
     * @throws Exception 
     * 
     */
    @RequestMapping("/foundScenic")
    public Scenic foundScenic(@RequestBody JSONObject data) throws Exception{
        Scenicmapper mapper=new Scenicmapper();
        return mapper.foundScenic(data.getString("name"));
    }
    @RequestMapping("/foundScenics")
    public ArrayList<Scenic> foundScenics(@RequestBody JSONObject data) throws Exception{
        Scenicmapper mapper=new Scenicmapper();
        return mapper.foundScenics(data.getString("message"),data.getString("label"));
    }
}
