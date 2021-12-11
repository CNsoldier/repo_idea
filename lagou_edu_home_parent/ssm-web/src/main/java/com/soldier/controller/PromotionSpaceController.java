package com.soldier.controller;

import com.soldier.domian.PromotionSpace;
import com.soldier.domian.ResponseResult;
import com.soldier.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/PromotionSpace")
public class PromotionSpaceController {

    @Autowired
    private PromotionSpaceService promotionSpaceService;

    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace(){

        List<PromotionSpace> list = promotionSpaceService.findAllPromotionSpace();

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", list);

        return responseResult;
    }

    /*
        添加广告位
     */
    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionSpace promotionSpace){

        if (promotionSpace.getId() == null){

            promotionSpaceService.savePromotionSpace(promotionSpace);

            ResponseResult responseResult = new ResponseResult(true, 200, "添加成功", null);
            return responseResult;
        }else {

            promotionSpaceService.updatePromotionSpace(promotionSpace);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改成功", null);
            return responseResult;
        }
    }

    /*
        根据ID查询广告位
     */
    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(int id){

        PromotionSpace promotionSpace = promotionSpaceService.findPromotionSpaceById(id);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", promotionSpace);
        return responseResult;
    }
}
