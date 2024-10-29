/*
 * @Author: WZB 150590206+BJlaoma@users.noreply.github.com
 * @Date: 2024-10-12 17:50:08
 * @LastEditors: WZB 150590206+BJlaoma@users.noreply.github.com
 * @LastEditTime: 2024-10-12 17:50:12
 * @FilePath: \TrapMap\src\main\java\com\TrapMap\service\GetAuditList.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
package com.TripMap.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.TripMap.pojo.AuditItem; // 假设有一个 AuditItem 类来表示待审核项

// 获取等待审核清单
@Service
public class GetAuditListService {

    // 假设有一个依赖注入的 Mapper 或 Repository 来访问数据库
    private final AuditMapper auditMapper;

    // 构造函数注入 AuditMapper
    public GetAuditListService(AuditMapper auditMapper) {
        this.auditMapper = auditMapper;
    }

    // 获取所有待审核项的方法
    public List<AuditItem> getAuditList() {
        return auditMapper.findAllPendingAudits();
    }
}

// 假设的 AuditMapper 接口，用于数据库操作
interface AuditMapper {
    List<AuditItem> findAllPendingAudits();
}

// 假设的 AuditItem 类，表示一个待审核项
class AuditItem {
    private String id;
    private String content;
    private String status;

    // 构造函数、getter 和 setter 省略
}
