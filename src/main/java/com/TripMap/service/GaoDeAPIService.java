package com.TripMap.service;

import com.TripMap.utils.HttpUtils;
import com.alibaba.fastjson.JSONObject;

import java.net.http.HttpResponse;

public class GaoDeAPIService {
    public JSONObject POIsearch(String keyword) throws Exception{
        String url="https://restapi.amap.com/v5/place/text";
        String params="key=ab52ced8d761815bccabf66462945293&keywords="+keyword+"&page_size=1&show_fields=tel,url,opentime_week,";
        HttpResponse<String> data=new HttpUtils().sendGet(url, params);
        JSONObject json=JSONObject.parseObject(data.body());
        if(json.getString("info").equals("OK")){
            return json.getJSONArray("pois").getJSONObject(0);
        }
        else{
            return null;
        }
    }






    public static void main(String[] args) throws Exception{
        GaoDeAPIService service=new GaoDeAPIService();
        System.out.println(service.POIsearch("华南理工大学"));
    }
}




