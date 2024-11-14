package com.TripMap.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import com.TripMap.pojo.AuditItem; // 假设有一个 AuditItem 类来表示待审核项
import com.TripMap.mapper.AuditMapper;
import com.TripMap.utils.SaveImageUtil;
import java.awt.image.BufferedImage;
import java.io.File;

// 获取等待审核清单
@Service
public class GetAuditListService {

    // 假设有一个依赖注入的 Mapper 或 Repository 来访问数据库
    private AuditMapper auditMapper;



    // 获取所有待审核项的方法
    public ArrayList<AuditItem> getAuditList() {
        auditMapper=new AuditMapper();
        return auditMapper.getAllPendingAudits();
    }

    public String postAuditItem(AuditItem auditItem) {
        auditMapper=new AuditMapper();
        auditMapper.addAuditItem(auditItem);
        return "success";
    }

    public ArrayList<AuditItem> getAuditItemByJudge(String judge){
        auditMapper=new AuditMapper();
        return auditMapper.getAuditItemByJudge(judge);
    }

    /**
     * @function 上传多张图片并返回URL列表
     * @param files 图片文件列表
     * @return ArrayList<String> 包含所有图片URL的列表
     * @throws Exception
     */
    public ArrayList<String> uploadPictureUrl(List<MultipartFile> files) throws Exception {
        ArrayList<String> urlList = new ArrayList<>();
        
        try {

            if(files.get(0).isEmpty()){
                return urlList;
            }
            for (MultipartFile file : files) {
                // 为每个文件生成唯一的文件名
                String fileName = System.currentTimeMillis() + ".png";
                // 使用与头像相同的保存路径
                String uploadDir = "/root/code/tripmap.Repo/src/main/resources/static/avatar/";
                String fullPath = uploadDir + fileName;
                
                // 确保目录存在
                new File(uploadDir).mkdirs();
                
                // 使用SaveImageUtil处理和保存图片
                BufferedImage bufferedImage = SaveImageUtil.convertMultipartFileToBufferedImage(file);
                SaveImageUtil.saveImageToFile(bufferedImage, fullPath);
                
                // 构建访问URL并添加到列表中
                String url = "http://8.138.145.205:8081/avatar/" + fileName;
                urlList.add(url);
            }
            
            return urlList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("保存图片失败：" + e.getMessage());
        }
    }
    public void updateAuditJudge(String auditItemId,String judge){
        auditMapper=new AuditMapper();
        auditMapper.updateAuditJudge(auditItemId, judge);
    }
    public AuditItem getAuditItemById(String auditItemId){
        auditMapper=new AuditMapper();
        return auditMapper.getAuditItemById(auditItemId);
    }

}



