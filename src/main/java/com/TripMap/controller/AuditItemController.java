package com.TripMap.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.TripMap.pojo.AuditItem;
import com.TripMap.pojo.JsonResult;
import com.TripMap.pojo.JsonlistResult;
import com.TripMap.pojo.Tag;
import com.TripMap.service.GetAuditListService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@RestController
@RequestMapping("/audit")
public class AuditItemController {
    @RequestMapping("/getAuditList")
    public JsonlistResult<AuditItem> getAuditList() {
        GetAuditListService service = new GetAuditListService();
        return new JsonlistResult<AuditItem>(service.getAuditList());
    }

    @PostMapping("/postAuditItem")
    public JsonResult<String> postAuditItem(@RequestParam("file") List<MultipartFile> file, @RequestParam("uuid") String uuid,
    @RequestParam("scenicName") String scenicName, @RequestParam("address") String address, @RequestParam("category") String category,
    @RequestParam("content") String content, @RequestParam("tags") JSONArray tags) throws Exception {
        GetAuditListService service = new GetAuditListService();
        ArrayList<Tag> tagList = new ArrayList<>();
        for (Object tag : tags) {
            String tagString = (String) tag;

            tagList.add(Tag.fromLabel(tagString));
        }
        AuditItem auditItem=new AuditItem(scenicName, address, category, tagList, content, uuid);
        ArrayList<String> pictureUrl=service.uploadPictureUrl(file);
        auditItem.setPictureUrl(pictureUrl);
        return new JsonResult<String>(service.postAuditItem(auditItem));
    }

    @GetMapping("/getPassAuditItem")
    public JsonlistResult<AuditItem> getPassAuditItem(){
        GetAuditListService service = new GetAuditListService();
        return new JsonlistResult<AuditItem>(service.getAuditItemByJudge("pass"));
    }

    @GetMapping("/getUnjudgedAuditItem")
    public JsonlistResult<AuditItem> getUnjudgedAuditItem(){
        GetAuditListService service = new GetAuditListService();
        return new JsonlistResult<AuditItem>(service.getAuditItemByJudge("unjudged"));
    }
    @PostMapping("/judge/pass")
    public JsonResult<String> passAuditItem(@RequestParam("auditItemId") String auditItemId){
        GetAuditListService service = new GetAuditListService();
        service.updateAuditJudge(auditItemId, "pass");
        return new JsonResult<String>("success");
    }
    @PostMapping("/judge/refused")
    public JsonResult<String> refusedAuditItem(@RequestParam("auditItemId") String auditItemId){
        GetAuditListService service = new GetAuditListService();
        service.updateAuditJudge(auditItemId, "refused");
        return new JsonResult<String>("success");
    }
}
