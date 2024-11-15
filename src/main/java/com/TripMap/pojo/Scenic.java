/*
 * @Author: WZB 150590206+BJlaoma@users.noreply.github.com
 * @Date: 2024-10-16 00:38:53
 * @LastEditors: WZB 150590206+BJlaoma@users.noreply.github.com
 * @LastEditTime: 2024-10-16 00:39:31
 * @FilePath: \TripMap\src\main\java\com\TripMap\pojo\ScenicAbstract.java
 */
package com.TripMap.pojo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.TripMap.mapper.Scenicmapper;
import com.alibaba.fastjson.JSONObject;

import lombok.Data;
//景点类
@Data
public class Scenic {
    protected String id;//景点id
    protected String name;//景点名字
    protected String description;//节点详细信息
    protected String location;//地点
    protected String latitude;//纬度的坐标
    protected String longitude;//经度的坐标
    protected String contact;//联系方式
    protected String openTime;
    protected String category;//景点类型，可以选择用固定tag
    protected ArrayList<Tag> taglist;
    protected ArrayList<Pair<String,String>> links;//前面是链接，后面是链接说明
    protected ArrayList<String> imagesURL;//景点图片连接
    protected LocalDateTime createdAt;//创建时间
    protected LocalDateTime updatedAt;//最后修改时间
    protected String recomendation;//推荐语
    protected String province; // 省份
    protected String city; // 城市

    @SuppressWarnings("unchecked")
    public Scenic(Document doc){
        this.id = doc.getString("id");
        this.name = doc.getString("name");
        this.description = doc.getString("description");
        this.location = doc.getString("location");
        this.latitude = doc.getString("latitude");
        this.longitude = doc.getString("longitude");
        this.contact = doc.getString("contact");
        this.openTime = doc.getString("openTime");
        this.category = doc.getString("category");

        
        // 处理 tags
        this.taglist = new ArrayList<>();
        @SuppressWarnings("unchecked")
        List<Document> tags = (List<Document>) doc.get("taglist");
        if (tags != null) {
            for (Document tagDoc : tags) {
                Tag tag = Tag.fromString(tagDoc.getString("tag")); // 假设 Tag 类有一个接受 Document 的构造函数
                this.taglist.add(tag);
            }
        }

        // 处理 links
        //有错误需要修改
        this.links = new ArrayList<>();
        @SuppressWarnings("unchecked")
        List<Document> linkDocs = (List<Document>) doc.get("links");
        if (linkDocs != null) {
            for (Document linkDoc : linkDocs) {
                String link = linkDoc.getString("first");
                String message = linkDoc.getString("second");
                this.links.add(new Pair<>(link, message)); // 假设 Pair 是一个简单的类
            }
        }

        // 处理 imagesURL
        this.imagesURL = (ArrayList<String>) doc.get("imagesURL");

        // 处理时间
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.createdAt = LocalDateTime.parse(doc.getString("createdAt"));
        this.updatedAt = LocalDateTime.parse(doc.getString("updatedAt"));
        
        this.recomendation = doc.getString("recomendation");
        this.province = doc.getString("province");
        this.city = doc.getString("city");
    }
    public Scenic(String name,String description,String location,String latitude,String longitude,String contact,String openTime,String category,ArrayList<Tag> taglist,ArrayList<Pair<String,String>> links,ArrayList<String> imagesURL,String recomendation,String province,String city){
        this.name=name;
        this.description=description;
        this.location=location;
        this.latitude=latitude;
        this.longitude=longitude;
        this.contact=contact;
        this.openTime=openTime;
        this.category=category;
        this.taglist=taglist;
        this.links=links;
        this.imagesURL=imagesURL;
        this.recomendation=recomendation;
        this.province=province;
        this.city=city;
        this.createdAt=LocalDateTime.now();
        this.updatedAt=LocalDateTime.now();
        this.id=String.valueOf(new Scenicmapper().ScenicCount()+1);
    }
    public Scenic(JSONObject data){
        this.name=data.getString("name");
        this.description=data.getString("description");
        this.location=data.getString("location");
        this.latitude=data.getString("latitude");
        this.longitude=data.getString("longitude");
        this.contact=data.getString("contact");
        this.openTime=data.getString("openTime");
        this.category=data.getString("category");
        this.taglist=new ArrayList<>();
        this.links=new ArrayList<>();
        this.imagesURL=new ArrayList<>();
        for(Object tag:data.getJSONArray("taglist")){
            this.taglist.add(Tag.StrtoTag(tag.toString()));
        }
        for(Object link:data.getJSONArray("links")){
            JSONObject linkObj=(JSONObject) link;
            this.links.add(new Pair<>(linkObj.getString("first"),linkObj.getString("second")));
        }
        for(Object image:data.getJSONArray("imagesURL")){
            this.imagesURL.add(image.toString());
        }
        this.recomendation=data.getString("recomendation");
        this.province=data.getString("province");
        this.city=data.getString("city");
        this.createdAt=LocalDateTime.now();
        this.updatedAt=LocalDateTime.now();
        this.id=String.valueOf(new Scenicmapper().ScenicCount()+1);
    }
}

