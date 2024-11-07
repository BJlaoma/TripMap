package com.TripMap.mapper;

import com.TripMap.pojo.Scenic;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.bson.Document;

@Repository
public class Scenicmapper {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 查找景点，精确查找
     * @param name 景点名字
     * @return Scenic
     */
    public Scenic findScenic(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        // 假设 Scenic 类有一个接受 Document 的构造函数
        return mongoTemplate.findOne(query, Scenic.class, "scenic");
    }

    /**
     * 模糊查找
     * @param message 查找的模糊信息
     * @param label 查找的标签，例如在名字中，在地区中等
     * @return Scenic 列表
     */
    public List<Scenic> findScenics(String message, String label) {
        Bson labelFilter = new Document(label, new Document("$regex", message));
        Query query = new Query((CriteriaDefinition) labelFilter);
        return mongoTemplate.find(query, Scenic.class, "scenic");
    }

    // 如果需要，可以在这里添加其他方法
}
