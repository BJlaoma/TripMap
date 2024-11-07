package com.TripMap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.TripMap.mapper.TagMapper; // 假设有一个 TagMapper 接口用于数据库操作
import com.TripMap.pojo.Tag; // 使用 Tag 类代替 TagDetail

import java.util.List;

@Service
public class TagInforGetService {

    // 自动注入 TagMapper
    @Autowired
    private TagMapper tagMapper;

    /**
     * 根据标签ID获取标签的详细信息
     * @param tagId 标签的ID
     * @return 标签的详细信息对象，如果未找到则返回 null
     */
    public Tag getTagDetailById(String tagId) {
        return tagMapper.findTagById(tagId);
    }

    /**
     * 获取所有标签的列表
     * @return 所有标签的列表
     */
    public List<Tag> getAllTags() {
        return tagMapper.findAllTags();
    }

    /**
     * 根据标签名称获取标签的详细信息
     * @param tagName 标签的名称
     * @return 标签的详细信息对象列表，如果未找到则返回空列表
     */
    /*public List<Tag> getTagDetailsByName(String tagName) {
        return tagMapper.findTagsByName(tagName);
    }*/
}