package com.TripMap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.TripMap.mapper.MapInfoMapper; // 假设有一个 MapInfoMapper 接口用于数据库操作
import com.TripMap.pojo.MapInfo; // 假设有一个 MapInfo 类表示地图信息

import java.util.List;

@Service
public class MapInforGetService {

    // 自动注入 MapInfoMapper
    @Autowired
    private MapInfoMapper mapInfoMapper;

    /**
     * 根据地点ID获取地图信息
     * @param placeId 地点的ID
     * @return 地图信息对象，如果未找到则返回 null
     */
    public MapInfo getMapInfoByPlaceId(String placeId) {
        return mapInfoMapper.findMapInfoByPlaceId(placeId);
    }

    /**
     * 搜索附近的景点
     * @param latitude 纬度
     * @param longitude 经度
     * @param radius 搜索半径（例如，以米为单位）
     * @return 附近的景点列表
     */
    public List<MapInfo> searchNearbyAttractions(double latitude, double longitude, int radius) {
        return mapInfoMapper.findNearbyAttractions(latitude, longitude, radius);
    }

    /**
     * 获取所有地图信息的列表
     * @return 所有地图信息的列表
     */
    public List<MapInfo> getAllMapInfo() {
        return mapInfoMapper.findAllMapInfo();
    }
}