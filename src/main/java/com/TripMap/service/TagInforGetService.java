package com.TripMap.service;

import com.TripMap.pojo.Tag;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class TagInforGetService {
    
    /**
     * 获取所有可用的标签
     * @return 返回所有Tag枚举值的列表
     */
    public List<Tag> getAllTags() {
        return Arrays.asList(Tag.values());
    }
}
