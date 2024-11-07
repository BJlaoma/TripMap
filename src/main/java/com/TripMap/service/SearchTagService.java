package com.TripMap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.TripMap.mapper.Scenicmapper; // 假设有一个 ScenicMapper 接口用于数据库操作
import com.TripMap.pojo.Scenic; // 假设有一个 Scenic 类表示景点信息
import com.TripMap.pojo.Tag; // 假设有一个 Tag 类表示标签

import java.util.List;

@Service
public class SearchTagService {

    // 自动注入 ScenicMapper
    @Autowired
    private Scenicmapper scenicMapper;

    /**
     * 根据标签搜索景点
     * @param tag 要搜索的标签
     * @return 匹配该标签的景点列表
     */
    public List<Scenic> searchScenicsByTag(Tag tag) {
        return scenicMapper.findScenicsByTag(tag);
    }

    /**
     * 获取所有可用的标签列表
     * @return 所有标签的列表
     */
    public List<Tag> getAllTags() {
        return scenicMapper.findAllTags();
    }
}