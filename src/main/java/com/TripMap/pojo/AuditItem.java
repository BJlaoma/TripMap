package com.TripMap.pojo;

public class AuditItem {
    private String id;
    private String content;
    private String status;

    // 构造函数
    public AuditItem() {
    }

    public AuditItem(String id, String content, String status) {
        this.id = id;
        this.content = content;
        this.status = status;
    }

    // Getter 和 Setter 方法
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
