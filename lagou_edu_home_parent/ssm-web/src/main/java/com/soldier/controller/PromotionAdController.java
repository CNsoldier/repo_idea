package com.soldier.controller;

import com.github.pagehelper.PageInfo;
import com.soldier.domian.PromotionAd;
import com.soldier.domian.PromotionAdVO;
import com.soldier.domian.ResponseResult;
import com.soldier.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    /*
        分页查询广告
     */
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(PromotionAdVO promotionAdVO){

        PageInfo<PromotionAd> pageInfo = promotionAdService.findAllPromotionAdByPage(promotionAdVO);

        ResponseResult responseResult = new ResponseResult(true,200,"查询成功",pageInfo);
        return responseResult;
    }

    /*
        添加广告
     */
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd){

        if (promotionAd.getId() == null){

            promotionAdService.savePromotionAd(promotionAd);

            ResponseResult responseResult = new ResponseResult(true, 200, "添加成功", null);
            return responseResult;
        }else {
            promotionAdService.updatePromotionAd(promotionAd);
            ResponseResult responseResult = new ResponseResult(true, 200, "更新成功", null);
            return responseResult;
        }
    }

    /*
        回显广告：根据id查询
     */
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(int id){

        PromotionAd promotionAd = promotionAdService.findPromotionAdById(id);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", promotionAd);
        return responseResult;
    }

    /*
        课程图片上传
     */
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult PromotionAdUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

        // 1.判断接收到的上传文件是否为空
        if(file.isEmpty()){

            throw new RuntimeException();
        }

        // 2.获取项目的部署路径
        // D:\Tomcat-8.5.55\webapps\ssm_web\
        String realPath = request.getServletContext().getRealPath("/");
        String substring = realPath.substring(0, realPath.indexOf("ssm-web"));

        // 3.获取原文件名
        String originalFilename = file.getOriginalFilename();

        // 4.生成新文件名
        String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));

        // 5.文件上传
        String uploadPath = substring + "upload\\";
        File filePath = new File(uploadPath, newFileName);

        // 如果目录不存在就创建目录
        if(!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录" + filePath);
        }

        // 图片进行了上传
        file.transferTo(filePath);

        // 6.将文件名和文件路径返回，进行响应
        Map<String, String> map = new HashMap<>();
        map.put("fileName",newFileName);
        map.put("filePath","http://localhost:8080/upload/" + newFileName);

        ResponseResult responseResult = new ResponseResult(true, 200, "图片上传成", map);

        return responseResult;
    }

    /*
        修改广告状态
     */
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(int id,int status){

        promotionAdService.updatePromotionAdStatus(id,status);

        Map<String, Integer> map = new HashMap<>();
        map.put("status",status);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", map);

        return responseResult;
    }
}
