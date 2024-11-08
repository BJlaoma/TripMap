package com.TripMap.controller;

import com.TripMap.pojo.JsonlistResult;
import com.TripMap.pojo.Scenic;
import com.TripMap.service.ScenicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/scenic")
public class CategoryController {

    private final ScenicService scenicService;

    @Autowired
    public CategoryController(ScenicService scenicService) {
        this.scenicService = scenicService;
    }

    @GetMapping("/categories")
    public JsonlistResult<String> getCategories() {
        // 获取所有景点
        ArrayList<Scenic> allScenics = new ArrayList<>(scenicService.getAllScenics());
        // 提取所有类别
        ArrayList<String> categories = allScenics.stream()
                .map(Scenic::getCategory)
                .filter(category -> category != null && !category.isEmpty())
                .distinct()  // 去重
                .collect(Collectors.toCollection(ArrayList::new));
        return new JsonlistResult<String>(categories);
    }
}