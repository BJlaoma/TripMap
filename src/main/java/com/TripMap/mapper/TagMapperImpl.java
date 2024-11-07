package com.TripMap.mapper;

import com.TripMap.pojo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagMapperImpl implements TagMapper {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void insertTag(Tag tag) {
        mongoTemplate.save(tag);
    }

    @Override
    public void deleteTagById(String tagId) {
        mongoTemplate.remove(new Query(Criteria.where("id").is(tagId)), Tag.class);
    }

    @Override
    public void updateTag(Tag tag) {
        mongoTemplate.save(tag);
    }

    @Override
    public Tag findTagById(String tagId) {
        return mongoTemplate.findById(tagId, Tag.class);
    }

    @Override
    public List<Tag> findAllTags() {
        return mongoTemplate.findAll(Tag.class);
    }
}