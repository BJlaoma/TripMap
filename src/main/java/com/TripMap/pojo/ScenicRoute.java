package com.TripMap.pojo;
/*
class：景点路线
*/

import java.util.ArrayList;

import lombok.Data;
@Data
public class ScenicRoute {
    ArrayList<ScenicAbstract> scenicRoute;//路线数据结构
    //String SRid;//路线id
    String name;//路线名字


    public ScenicRoute(String name){
        scenicRoute=new ArrayList<>();
        this.name=name;
    }
    /**
     * @function 插入景点
     * @param S 插入的景点
     * @param index 插入的位置，插入在index后一位，不填默认插入到最后 
     * @author wzb
     */
    public void InsertScenic(ScenicAbstract S,Integer index){
        if(index<scenicRoute.size()){
            scenicRoute.add(index, S);
        }
    }
    public void InsertScenic(ScenicAbstract S){
        scenicRoute.add(S);
    }

    /**
     * @function 交换两个景点的顺序
     * @param first 将要交换的景点，在前面的
     * @param second 将要交换的景点，在后面的
     * @author wzb
     */
    public void SwitchScenic(Integer first,Integer second){
        ScenicAbstract a=scenicRoute.get(first);
        scenicRoute.set(first,scenicRoute.get(second));
        scenicRoute.set(second,a);
    }

    /**
     * @function 删除景点
     * @param index 景点的索引位置
     */
    public void DeleteScenic(Integer index){
        if(index<scenicRoute.size()){
            scenicRoute.remove(index);            
        }
    }
}
