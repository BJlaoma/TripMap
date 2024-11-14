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
        String latitude=json.getString("location").split(",")[0];
        String longitude=json.getString("location").split(",")[1];
        Scenic scenic=new Scenic(Item.getScenicName(), Item.getContent(),
         json.getString("pname")+json.getString("cityname")+json.getString("address")
         , latitude, longitude, json.getString("tel"),
         json.getString("opentime_week"), Item.getCategory(), Item.getTags()
         , new ArrayList<Pair<String,String>>(),Item.getPictureUrl(),"",
         json.getString("province"), json.getString("city"));
        return scenic;
    }

    public static void main(String[] args) throws Exception{
        System.out.println(new ItemToScenicService().itemToScenic("57c1aa81-f4f8-4a27-9a7e-d73bb22440b0"));
    }
}
