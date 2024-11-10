package com.TripMap.service;

import com.TripMap.mapper.Favorsmapper;
import com.TripMap.mapper.Usermapper;
import com.TripMap.utils.SaveImageUtil;
import com.alibaba.fastjson.JSONObject;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.TripMap.pojo.User;
import com.TripMap.pojo.Userfavors;    

public class Userservice {
    /**
     * @function 更新用户头像
     * @param uuid 用户唯一标识符
     * @param image 用户头像
     * @return JSONObject,包含头像url地址
     * @throws Exception
     */
    public JSONObject UpdateAvatar(String uuid, MultipartFile file) throws Exception {
        JSONObject result = new JSONObject();
        try {
            // 生成唯一的文件名
            String fileName = uuid + "_" + System.currentTimeMillis() + ".png";
            // 使用绝对路径
            String uploadDir = "/root/code/tripmap.Repo/src/main/resources/static/avatar/";
            //String uploadDir = "C:/Users/15017/Desktop/JAVA/项目想法/TripMap/TrapMap/TripMap/src/main/resources/static/avatar/";
            String fullPath = uploadDir + fileName;
            
            // 确保目录存在
            new File(uploadDir).mkdirs();
            
            BufferedImage bufferedImage = SaveImageUtil.convertMultipartFileToBufferedImage(file);
            SaveImageUtil.saveImageToFile(bufferedImage, fullPath);
            
            // 构建访问URL
            String url = "http://8.138.145.205:8081/avatar/" + fileName;  
            //String url = "http://127.0.0.1:8080/avatar/" + fileName;  
            result.put("url", url);
            result.put("uuid", uuid);
            // 更新用户头像URL
            UpdateAvatarUrl(uuid, url);
            
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("保存头像失败：" + e.getMessage());
        }
    }

    /**
     * @function 更新用户头像URL
     * @param uuid 用户唯一标识符
     * @param avatarUrl 头像URL
     * @throws Exception
     */
    public void UpdateAvatarUrl(String uuid, String avatarUrl) throws Exception {
        Usermapper mapper = new Usermapper();
        mapper.updateAvatarUrl(uuid, avatarUrl);
    }

    /**
     * @function 更新用户信息
     * @param uuid 用户唯一标识符
     * @param json 用户信息，只能修改名字
     * @return 用户对象
     * @throws Exception
     */
    public User UpdateUser(String uuid,JSONObject json) throws Exception{
        Usermapper mapper = new Usermapper();
        
        // 检查并更新用户名
        if (json.containsKey("name")) {
            String newName = json.getString("name");
            mapper.updateName(uuid, newName);
        }
        
        // 不允许更新的字段：uuid, password, avatarUrl
        if (json.containsKey("password") || json.containsKey("avatarUrl")) {
            throw new Exception("不允许修改 UUID、密码或头像URL");
        }
        
        // 更新数据库
        return mapper.getUserByUUID(uuid);
    }
    public Userfavors getUserfavors(String uuid) throws Exception{
        Favorsmapper mapper = new Favorsmapper();
        return mapper.getUserfavors(uuid);
    }
    public void updateScenicID(String uuid,String id) throws Exception{
        Favorsmapper mapper = new Favorsmapper();
        mapper.updateScenicID(uuid, id);
    }
    public String insertScenicID(String uuid,String id) throws Exception{
        Favorsmapper mapper = new Favorsmapper();
        if(mapper.isExist(uuid, id)){
            return "收藏已经存在";
        }
        mapper.insertScenicID(uuid, id);   
        return "收藏添加成功";
    }
    public void deleteScenicID(String uuid,String id) throws Exception{
        Favorsmapper mapper = new Favorsmapper();
        mapper.deleteScenicID(uuid, id);
    }

    /**
     * @function 更新用户收藏，对比传入的id列表与数据库中的收藏进行同步
     * @param uuid 用户唯一标识符
     * @param newFavoriteIds 新的收藏ID列表
     * @return JSONObject 包含添加和删除的收藏ID列表
     * @throws Exception
     */
    public JSONObject updateUserfavors(String uuid, List<String> newFavoriteIds) throws Exception {
        Favorsmapper mapper = new Favorsmapper();
        JSONObject result = new JSONObject();
        List<String> addedIds = new ArrayList<>();
        List<String> removedIds = new ArrayList<>();
        
        // 获取用户当前的收藏
        Userfavors currentFavors = mapper.getUserfavors(uuid);
        List<String> currentIds = currentFavors.getId();
        
        // 处理新传入的每个ID
        for (String id : newFavoriteIds) {
            if (!currentIds.contains(id)) {
                // 如果当前收藏中没有这个ID，添加它
                mapper.insertScenicID(uuid, id);
                addedIds.add(id);
            } else {
                // 如果当前收藏中有这个ID，删除它
                mapper.deleteScenicID(uuid, id);
                removedIds.add(id);
            }
        }
        
        // 将添加和删除的ID列表放入结果中
        result.put("addedFavorites", addedIds);
        result.put("removedFavorites", removedIds);
        
        return result;
    }
}
