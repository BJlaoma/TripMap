package com.TripMap.pojo;

import lombok.Data;
import org.bson.Document;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class AuditItem {
    private String auditItemId;
    private String scenicName;
    private String address;
    private String category;
    private ArrayList<Tag> tags;
    private String content;
    private String judge;
    private ArrayList<String> pictureUrl;
    private String uuid;

    // 默认构造函数
    public AuditItem() {
    }

    /**
     * 普通赋值构造函数
     */
    public AuditItem(String auditItemId, String scenicName, String address, 
                    String category, ArrayList<Tag> tags, String content, 
                    String judge, ArrayList<String> pictureUrl, String uuid) {
        this.auditItemId = auditItemId;
        this.scenicName = scenicName;
        this.address = address;
        this.category = category;
        this.tags = tags;
        this.content = content;
        this.judge = "unjudged";
        this.pictureUrl = pictureUrl;
        this.uuid = uuid;
    }
    public AuditItem(String scenicName, String address, String category, ArrayList<Tag> tags, String content, String uuid){
        this.scenicName = scenicName;
        this.address = address;
        this.category = category;
        this.tags = tags;
        this.content = content;
        this.uuid = uuid;
        this.judge = "unjudged";
        this.pictureUrl = new ArrayList<>();
        this.auditItemId = UUID.randomUUID().toString();
    }
    /**
     * 使用 Document 构造函数
     */
    public AuditItem(Document doc) {
        this.auditItemId = doc.getString("auditItemId");
        this.scenicName = doc.getString("scenicName");
        this.address = doc.getString("address");
        this.category = doc.getString("category");
        this.content = doc.getString("content");
        this.judge = doc.getString("judge");
        this.uuid = doc.getString("uuid");
        
        // 处理 tags 数组
        List<String> tagStrings = doc.getList("tags", String.class);
        if (tagStrings != null) {
            this.tags = new ArrayList<>(tagStrings.stream()
                    .map(Tag::fromString)
                    .collect(Collectors.toList()));
        } else {
            this.tags = new ArrayList<>();
        }

        // 处理 pictureUrl 数组
        List<String> pictures = doc.getList("pictureUrl", String.class);
        if (pictures != null) {
            this.pictureUrl = new ArrayList<>(pictures);
        } else {
            this.pictureUrl = new ArrayList<>();
        }
    }

    public AuditItem(JSONObject data) {
        this.auditItemId = data.getString("auditItemId");
        this.scenicName = data.getString("scenicName");
        this.address = data.getString("address");
        this.category = data.getString("category");
        this.content = data.getString("content");
        this.judge = data.getString("judge");
        this.uuid = data.getString("uuid");
        this.pictureUrl = new ArrayList<>(data.getJSONArray("pictureUrl").toJavaList(String.class));
    }
    public String pass(){
        this.judge="pass";
        return "审核通过";
    }
    public String reject(){
        this.judge="reject";
        return "审核不通过";
    }
}
