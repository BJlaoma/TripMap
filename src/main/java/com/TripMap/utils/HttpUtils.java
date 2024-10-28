package com.TripMap.utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpUtils {
    HttpClient client;
    HttpRequest request;
    HttpResponse<String> response;
    /**
     * @function 发送GET请求
     * @param url 请求地址
     * @param params 请求参数
     * @return 响应数据,HttpResponse<String>
     * @throws Exception
     */
    public HttpResponse<String> sendGet(String url,String params) throws Exception{
        client=HttpClient.newHttpClient();
        request=HttpRequest.newBuilder().uri(URI.create(url+"?"+params)).build();
        response=client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }
}
