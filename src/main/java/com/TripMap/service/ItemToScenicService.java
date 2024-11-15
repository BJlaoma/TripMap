package com.TripMap.service;

import java.util.ArrayList;

import com.TripMap.pojo.AuditItem;
import com.TripMap.pojo.Pair;
import com.TripMap.pojo.Scenic;
import com.alibaba.fastjson.JSONObject;

public class ItemToScenicService {
    public JSONObject GetMsgByGaoDe(String auditItemId) throws Exception{
        AuditItem Item=new GetAuditListService().getAuditItemById(auditItemId);
        return new GaoDeAPIService().POIsearch(Item.getScenicName());
    }

    public Scenic itemToScenic(String auditItemId) throws Exception{
        JSONObject json=GetMsgByGaoDe(auditItemId);
        AuditItem Item=new GetAuditListService().getAuditItemById(auditItemId);
        String latitude=json.getString("location").split(",")[1];
        String longitude=json.getString("location").split(",")[0];
        Scenic scenic=new Scenic(Item.getScenicName(), Item.getContent(),
         json.getString("pname")+json.getString("cityname")+json.getString("address")
         , latitude, longitude, json.getString("tel"),
         json.getString("opentime_week"), Item.getCategory(), Item.getTags()
         , new ArrayList<Pair<String,String>>(),Item.getPictureUrl(),"",
         json.getString("pname"), json.getString("cityname"));
        return scenic;
    }

    public static void main(String[] args) throws Exception{
        System.out.println(new ItemToScenicService().itemToScenic("5aa6e03d-933b-409b-984a-79fdd3f90bbc"));
    }
}
