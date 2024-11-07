package com.TripMap.mapper;

import com.TripMap.pojo.Tag;
import java.util.List;

public interface TagMapper {
    /**
     * 插入一个新的标签
     * @param tag 要插入的标签对象
     */
    void insertTag(Tag tag);

    /**
     * 根据标签ID删除一个标签
     * @param tagId 要删除的标签ID
     */
    void deleteTagById(String tagId);

    /**
     * 更新一个标签
     * @param tag 要更新的标签对象
     */
    void updateTag(Tag tag);

    /**
     * 根据标签ID获取一个标签
     * @param tagId 要获取的标签ID
     * @return 对应的标签对象
     */
    Tag findTagById(String tagId);

    /**
     * 获取所有标签的列表
     * @return 所有标签的列表
     */
    List<Tag> findAllTags();
}
