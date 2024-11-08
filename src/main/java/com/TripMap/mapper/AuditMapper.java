package com.TripMap.mapper;

import com.TripMap.pojo.AuditItem;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.util.ArrayList;

public class AuditMapper extends mapper {
    private MongoCollection<Document> collection;

    public AuditMapper() {
        super();
        collection = super.getDatabase().getCollection("audit");
    }

    /**
     * 添加新的审核项
     * @param auditItem 审核项对象
     */
    public void addAuditItem(AuditItem auditItem) {
        Document doc = new Document()
                .append("auditItemId", auditItem.getAuditItemId())
                .append("scenicName", auditItem.getScenicName())
                .append("address", auditItem.getAddress())
                .append("category", auditItem.getCategory())
                .append("content", auditItem.getContent())
                .append("judge", auditItem.getJudge())
                .append("pictureUrl", auditItem.getPictureUrl())
                .append("uuid", auditItem.getUuid());

        // 处理tags数组
        ArrayList<String> tagStrings = new ArrayList<>();
        if (auditItem.getTags() != null) {
            auditItem.getTags().forEach(tag -> tagStrings.add(tag.getLabel()));
        }
        doc.append("tags", tagStrings);

        collection.insertOne(doc);
    }

    /**
     * 获取所有待审核项目
     * @return 待审核项目列表
     */
    public ArrayList<AuditItem> getAllPendingAudits() {
        Bson filter = Filters.eq("judge", "unjudged");
        FindIterable<Document> documents = collection.find(filter);
        
        ArrayList<AuditItem> auditItems = new ArrayList<>();
        for (Document doc : documents) {
            auditItems.add(new AuditItem(doc));
        }
        return auditItems;
    }

    /**
     * 通过ID获取审核项
     * @param auditItemId 审核项ID
     * @return 审核项对象
     */
    public AuditItem getAuditItemById(String auditItemId) {
        Bson filter = Filters.eq("auditItemId", auditItemId);
        Document doc = collection.find(filter).first();
        return doc != null ? new AuditItem(doc) : null;
    }

    /**
     * 更新审核项状态
     * @param auditItemId 审核项ID
     * @param judge 新状态
     */
    public void updateAuditJudge(String auditItemId, String judge) {
        Bson filter = Filters.eq("auditItemId", auditItemId);
        Document update = new Document("$set", new Document("judge", judge));
        collection.updateOne(filter, update);
    }

    /**
     * 删除审核项
     * @param auditItemId 审核项ID
     */
    public void deleteAuditItem(String auditItemId) {
        Bson filter = Filters.eq("auditItemId", auditItemId);
        collection.deleteOne(filter);
    }
    public ArrayList<AuditItem> getAuditItemByJudge(String judge){
        Bson filter = Filters.eq("judge", judge);
        FindIterable<Document> documents = collection.find(filter);
        ArrayList<AuditItem> auditItems = new ArrayList<>();
        for (Document doc : documents) {
            auditItems.add(new AuditItem(doc));
        }
        return auditItems;
    }
}
