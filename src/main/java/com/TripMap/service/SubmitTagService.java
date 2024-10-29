package com.TripMap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.TripMap.mapper.TagMapper; // 假设有一个 TagMapper 接口用于数据库操作
import com.TripMap.pojo.Tag; // 假设有一个 Tag 类表示标签
import com.TripMap.pojo.Scenic; // 假设有一个 Scenic 类表示景点

import java.util.List;

@Service
public class SubmitTagService {

    // 自动注入 TagMapper
    @Autowired
    private TagMapper tagMapper;

    /**
     * 提交一个新的标签
     * @param tag 要提交的标签对象
     * @return 提交成功的标签对象，如果提交失败则返回 null
     */
    public Tag submitNewTag(Tag tag) {
        try {
            tagMapper.insertTag(tag);
            return tag;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 更新现有的标签
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
     * 删除一个标签
     * @param tagId 要删除的标签ID
     * @return 如果删除成功返回 true，否则返回 false
     */
    public boolean deleteTag(String tagId) {
        try {
            tagMapper.deleteTag(tagId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 为景点添加标签
     * @param scenicId 景点的ID
     * @param tagId 标签的ID
     * @return 如果添加成功返回 true，否则返回 false
     */
    public boolean addTagToScenic(String scenicId, String tagId) {
        try {
            tagMapper.addTagToScenic(scenicId, tagId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 从景点中移除标签
     * @param scenicId 景点的ID
     * @param tagId 标签的ID
     * @return 如果移除成功返回 true，否则返回 false
     */
    public boolean removeTagFromScenic(String scenicId, String tagId) {
        try {
            tagMapper.removeTagFromScenic(scenicId, tagId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
