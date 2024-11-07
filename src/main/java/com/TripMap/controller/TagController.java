package com.TripMap.controller;

import com.TripMap.pojo.Tag;
import com.TripMap.service.TagInforGetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/scenic")
public class TagController {

    private final TagInforGetService tagService;

    @Autowired
    public TagController(TagInforGetService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/tags")
    public ResponseEntity<List<Tag>> getTags() {
        List<Tag> tags = tagService.getAllTags();
        return ResponseEntity.ok(tags);
    }
}