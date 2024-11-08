
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
    // 根据标签获取对应的枚举常量
    public static Tag fromLabel(String label) {
        for (Tag tag : Tag.values()) {
            if (tag.getLabel().equals(label)) {
                return tag;
            }
        }
        throw new IllegalArgumentException("没有找到对应的标签: " + label);
    }
}