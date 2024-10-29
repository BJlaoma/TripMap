/*
 * @Author: WZB 150590206+BJlaoma@users.noreply.github.com
 * @Date: 2024-10-17 19:57:25
 * @LastEditors: WZB 150590206+BJlaoma@users.noreply.github.com
 * @LastEditTime: 2024-10-29 19:40:22
 * @FilePath: \TripMap\src\main\java\com\TripMap\pojo\Tag.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
package com.TripMap.pojo;

public enum Tag {
    NicePrice("性价比高"),
    HISTORICAL("历史悠久"),MUSEUM("博物馆"),LAKE("湖泊"),
    CULTURE("文化遗产"),MOUNTAIN("山脉"),PARK("公园");
    private String label;

    // 构造函数
    Tag(String label) {
        this.label = label;
    }

    // 获取标签的字符串表示
    public String getLabel() {
        return label;
    }
    public void setLabel(String label){
        this.label=label;
    }

    // 根据字符串获取对应的 Tag
    public static Tag fromString(String label) {
        for (Tag tag : Tag.values()) {
            if (tag.label.equalsIgnoreCase(label)) {
                return tag;
            }
        }
        throw new IllegalArgumentException("No constant with label " + label + " found");
    }
}