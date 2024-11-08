package com.TripMap.service;

import com.TripMap.mapper.Scenicmapper;
import com.TripMap.pojo.Scenic;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ScenicService {
    
    private Scenicmapper scenicmapper;

    public ScenicService() {}

    /**
     * 获取所有景点信息
     * @return 返回所有景点的列表
     */
    public List<Scenic> getAllScenics() {
        this.scenicmapper = new Scenicmapper();
        return scenicmapper.getScenices();
    }
} 