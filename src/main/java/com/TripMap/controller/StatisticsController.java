package com.TripMap.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TripMap.Interceptor.MyInterceptor;
import com.TripMap.pojo.JsonResult;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    
    @GetMapping("/visits")
    public JsonResult<Long> getTotalVisits() {
        return new JsonResult<Long>(MyInterceptor.getTotalVisits());
    }
} 