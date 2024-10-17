/*
 * @Author: WZB 150590206+BJlaoma@users.noreply.github.com
 * @Date: 2024-10-16 00:38:53
 * @LastEditors: WZB 150590206+BJlaoma@users.noreply.github.com
 * @LastEditTime: 2024-10-16 00:39:31
 * @FilePath: \TripMap\src\main\java\com\TripMap\pojo\ScenicAbstract.java
 */
package com.TripMap.pojo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
//景点抽象类
public abstract class ScenicAbstract {
    protected String id;//景点id
    protected String name;//景点名字
    protected String description;//节点详细信息
    protected String location;//地点
    protected String phone;
    protected String openTime;
    protected String category;//景点类型，可以选择用固定tag
    protected ArrayList<Tag> taglist;
    protected List<Pair<String,String>> links;//前面是链接，后面是链接说明
    protected List<String> imagesURL;//景点图片连接
    protected LocalDateTime createdAt;//创建时间
    protected LocalDateTime updatedAt;//最后修改时间
}

