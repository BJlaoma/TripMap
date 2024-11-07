package com.TripMap.mapper;

import com.TripMap.pojo.AuditItem;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
// 假设的 AuditMapper 接口，用于数据库操作
public interface AuditMapper {
    List<AuditItem> findAllPendingAudits();
}
