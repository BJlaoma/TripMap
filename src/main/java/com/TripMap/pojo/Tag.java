package com.TripMap.pojo;

public enum Tag {
    NicePrice("性价比高"),
    HISTORICAL("历史悠久");

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