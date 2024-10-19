package com.TripMap.pojo;

import java.util.ArrayList;

public class JsonlistResult<T> {
    ArrayList<T> data;
    private Integer code;
    private String msg;

    /**
     * 若没有数据返回，默认状态码为 0，提示信息为“操作成功！”
     */
    public JsonlistResult() {
        this.code = 0;
        this.msg = "操作成功！";
    }

    /**
     * 若没有数据返回，可以人为指定状态码和提示信息
     * @param code=500
     * @param msg
     */
    public JsonlistResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 有数据返回时，状态码为 200，默认提示信息为“操作成功！”
     * @param data
     */
    public JsonlistResult(ArrayList<T> data) {
        this.data = data;
        this.code = 200;
        this.msg = "操作成功！";
    }

    /**
     * 有数据返回，状态码为200，人为指定提示信息
     * @param data
     * @param msg
     */
    public JsonlistResult(ArrayList<T> data, String msg) {
        this.data = data;
        this.code = 200;
        this.msg = msg;
    }

    public ArrayList<T> getData() {
        return data;
    }

    public void setData(ArrayList<T> data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
}
