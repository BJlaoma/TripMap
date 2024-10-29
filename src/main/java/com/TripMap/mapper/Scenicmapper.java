/*
 * @Author: WZB 150590206+BJlaoma@users.noreply.github.com
 * @Date: 2024-10-19 21:03:02
 * @LastEditors: WZB 150590206+BJlaoma@users.noreply.github.com
 * @LastEditTime: 2024-10-29 19:21:30
 * @FilePath: \TripMap\src\main\java\com\TripMap\mapper\Scenicmapper.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
package com.TripMap.mapper;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.TripMap.pojo.Scenic;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

public class Scenicmapper extends mapper{
        
    MongoCollection<Document> collection;

    public Scenicmapper(){
        super();
        collection=super.getDatabase().getCollection("scenic");
    }

    /**
     * @param name 景点名字
     * @function 查找景点，精确查找
     * @return Scenic
     * @throws Exception 
     */
    public Scenic foundScenic(String name) throws Exception{
           Bson filter=Filters.eq("name",name);
           Document doc=collection.find(filter).first();
           if(doc==null){
            throw new Exception("没有这个景点");
            }
            return new Scenic(doc);
    }

    /**
     * @param massage 查找的模糊信息
     * @param label 查找的标签，例如在名字中，在地区中等
     * @function 模糊查找
     * @return Scenic
     */
    public ArrayList<Scenic> foundScenics(String massage,String label){
        Bson labelfilter=Filters.regex(label, massage);
        FindIterable<Document> result=collection.find(labelfilter);
        ArrayList<Scenic> array=new ArrayList<>();
        if(result.first()!=null){
            for(Document doc : result){
                array.add(new Scenic(doc));
            }
        }
        return array;
    }

    /**
     * @function 获取所有景点
     * @return ArrayList<Scenic>
     */
    public ArrayList<Scenic> getScenices(){
        FindIterable<Document> result=collection.find();
        ArrayList<Scenic> array=new ArrayList<>();
        if(result.first()!=null){
            for(Document doc : result){
                array.add(new Scenic(doc));
            }
        }
        return array;
    }
}
