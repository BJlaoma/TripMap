/*
 * @Author: WZB 150590206+BJlaoma@users.noreply.github.com
 * @Date: 2024-10-12 17:06:58
 * @LastEditors: WZB 150590206+BJlaoma@users.noreply.github.com
 * @LastEditTime: 2024-10-12 17:07:02
 * @FilePath: \TrapMap\src\main\java\com\TrapMap\service\TagInforGetService.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
package com.TripMap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.TripMap.mapper.TagMapper; // 假设有一个 TagMapper 接口用于数据库操作
import com.TripMap.pojo.TagDetail; // 假设有一个 TagDetail 类表示标签的详细信息
import com.TripMap.pojo.Tag; // 假设有一个 Tag 类表示标签

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
    public TagDetail getTagDetailById(String tagId) {
        return tagMapper.findTagDetailById(tagId);
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
    public List<TagDetail> getTagDetailsByName(String tagName) {
        return tagMapper.findTagDetailsByName(tagName);
    }
}
