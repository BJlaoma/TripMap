/*
 * @Author: WZB 150590206+BJlaoma@users.noreply.github.com
 * @Date: 2024-10-16 00:38:53
 * @LastEditors: WZB 150590206+BJlaoma@users.noreply.github.com
 * @LastEditTime: 2024-10-16 00:39:31
 * @FilePath: \TripMap\src\main\java\com\TripMap\pojo\ScenicAbstract.java
 */
package com.TripMap.pojo;

import java.time.LocalDateTime;
import java.util.List;
//景点抽象类
public abstract class ScenicAbstract {
    private String id;//景点id
    private String name;//景点名字
    private String description;//节点详细信息
    private String location;//地点
    private String phone;
    private String openTime;
    private String category;//景点类型，可以选择用固定tag
    private List<Pair<String,String>> links;//前面是链接，后面是链接说明
    private List<String> imagesURL;//景点图片连接
    private LocalDateTime createdAt;//创建时间
    private LocalDateTime updatedAt;//最后修改时间
}

