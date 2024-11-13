package com.TripMap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TripMap.Interceptor.MyInterceptor;
import com.TripMap.pojo.JsonResult;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    
    @Autowired
    MyInterceptor myInterceptor;
    @GetMapping("/visits")
    public JsonResult<Long> getTotalVisits() {
        return new JsonResult<Long>(myInterceptor.getTotalVisits());
    }
} 