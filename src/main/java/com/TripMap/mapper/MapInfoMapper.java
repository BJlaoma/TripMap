package com.TripMap.mapper;

import com.TripMap.pojo.Scenic;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.util.ArrayList;
import java.util.List;
import com.mongodb.client.FindIterable;

public class MapInfoMapper {
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;

    public MapInfoMapper(MongoDatabase database) {
        this.database = database;
        this.collection = database.getCollection("scenic");
    }

    /**
     * 根据地点ID获取地图信息
     * @param placeId 地点的ID
     * @return 地图信息对象，如果未找到则返回 null
     */
    public Scenic findScenicByPlaceId(String placeId) {
        Document doc = collection.find(new Document("placeId", placeId)).first();
        if (doc != null) {
            return new Scenic(doc); // 假设Scenic类有一个接受Document的构造函数
        }
        return null;
    }

    /**
     * 搜索附近的景点
     * @param latitude 纬度
     * @param longitude 经度
     * @param radius 搜索半径（例如，以米为单位）
     * @return 附近的景点列表
     */
    public List<Scenic> findNearbyAttractions(double latitude, double longitude, int radius) {
        // 构建 $near 查询条件
        Bson geoNear = new Document("location",
                new Document("$near",
                        new Document("$geometry",
                                new Document("type", "Point")
                                        .append("coordinates", new double[]{longitude, latitude}))
                                .append("$maxDistance", radius)));

        FindIterable<Document> iterable = collection.find(geoNear);
        List<Scenic> scenics = new ArrayList<>();
        for (Document doc : iterable) {
            scenics.add(new Scenic(doc)); // 假设Scenic类有一个接受Document的构造函数
        }
        return scenics;
    }

    /**
     * 获取所有地图信息的列表
     * @return 所有地图信息的列表
     */
    public List<Scenic> findAllScenics() {
        FindIterable<Document> iterable = collection.find();
        List<Scenic> scenics = new ArrayList<>();
        for (Document doc : iterable) {
            scenics.add(new Scenic(doc)); // 假设Scenic类有一个接受Document的构造函数
        }
        return scenics;
    }
}