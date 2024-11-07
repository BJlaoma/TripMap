package com.TripMap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.TripMap.mapper.Scenicmapper;
import com.TripMap.pojo.Scenic;
import java.util.List;

@Service
public class ScenicService {

    @Autowired
    private Scenicmapper scenicMapper;

    public Scenic getScenicById(String id) {
        return scenicMapper.findScenicById(id);
    }

    public List<Scenic> getAllScenics() {
        return scenicMapper.findAllScenics();
    }

    // 添加其他需要的方法
}