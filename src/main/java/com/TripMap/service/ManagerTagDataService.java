package com.TripMap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.TripMap.mapper.TagMapper; // 假设有一个 TagMapper 接口用于数据库操作
import com.TripMap.pojo.Tag; // 假设有一个 Tag 类表示标签

@Service
public class ManagerTagDataService {

    // 自动注入 TagMapper
    @Autowired
    private TagMapper tagMapper;

    /**
     * 添加一个新的标签
     * @param tag 要添加的标签对象
     * @return 添加成功的标签对象，如果添加失败则返回 null
     */
    public Tag addTag(Tag tag) {
        try {
            tagMapper.insertTag(tag);
            return tag;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据标签ID删除一个标签
     * @param tagId 要删除的标签ID
     * @return 如果删除成功返回 true，否则返回 false
     */
    public boolean deleteTagById(String tagId) {
        try {
            tagMapper.deleteTagById(tagId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据标签ID更新一个标签
     * @param tag 要更新的标签对象
     * @return 更新成功的标签对象，如果更新失败则返回 null
     */
    public Tag updateTag(Tag tag) {
        try {
            tagMapper.updateTag(tag);
            return tag;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据标签ID获取一个标签
     * @param tagId 要获取的标签ID
     * @return 对应的标签对象，如果未找到则返回 null
     */
    public Tag getTagById(String tagId) {
        return tagMapper.findTagById(tagId);
    }

    /**
     * 获取所有标签的列表
     * @return 所有标签的列表
     */
    public Iterable<Tag> getAllTags() {
        return tagMapper.findAllTags();
    }
}
