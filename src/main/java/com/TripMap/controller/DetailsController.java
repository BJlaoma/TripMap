package com.TripMap.controller;

import com.TripMap.pojo.Scenic;
import com.TripMap.service.ScenicService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scenic")
public class DetailsController {

    private final ScenicService scenicService;

    @Autowired
    public DetailsController(ScenicService scenicService) {
        this.scenicService = scenicService;
    }
/*
 *     @RequestMapping("/getScenicDetails")
    public ResponseEntity<Scenic> getScenicDetails(@RequestBody JSONObject data) {
        Scenic scenic = scenicService.getScenicById(data.getString("id"));
        if (scenic != null) {
            return ResponseEntity.ok(scenic);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
 */

}
