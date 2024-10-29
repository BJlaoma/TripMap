package com.TripMap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.TripMap.mapper.ScenicMapper; // 假设有一个 ScenicMapper 接口用于数据库操作
import com.TripMap.pojo.Scenic; // 假设有一个 Scenic 类表示景点信息

import java.util.List;

@Service
public class RecommandListGetService {

    // 自动注入 ScenicMapper
    @Autowired
    private ScenicMapper scenicMapper;

    /**
     * 获取推荐景点列表
     * @return 推荐景点的列表
     */
    public List<Scenic> getRecommandList() {
        return scenicMapper.findRecommandScenics();
    }

    /**
     * 根据特定条件获取推荐景点列表，例如基于用户的偏好或热门景点
     * @param criteria 搜索条件，可以是用户偏好、评分、距离等
     * @return 根据条件筛选后的推荐景点列表
     */
    public List<Scenic> getRecommandListByCriteria(String criteria) {
        // 这里只是一个示例，具体的实现将取决于你的业务逻辑和数据库设计
        return scenicMapper.findRecommandScenicsByCriteria(criteria);
    }
}
