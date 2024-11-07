package com.TripMap.controller;

import com.TripMap.pojo.Scenic;
import com.TripMap.service.ScenicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
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
    public ResponseEntity<Set<String>> getCategories() {
        // 获取所有景点
        List<Scenic> allScenics = scenicService.getAllScenics();
        // 提取所有独特的类别
        Set<String> categories = allScenics.stream()
                .map(Scenic::getCategory) // 假设Scenic类有getCategory方法
                .filter(category -> category != null && !category.isEmpty())
                .collect(Collectors.toSet());
        return ResponseEntity.ok(categories);
    }
}