package com.soldier.service;

import com.soldier.domian.PromotionSpace;

import java.util.List;

public interface PromotionSpaceService {

    /*
        获取所有广告位的方法
     */
    public List<PromotionSpace> findAllPromotionSpace();

    /*
        添加广告位
     */
    public void savePromotionSpace(PromotionSpace promotionSpace);

    /*
        回显广告位(根据ID查询广告位信息)
     */
    public PromotionSpace findPromotionSpaceById(int id);

    /*
        修改广告位
     */
    public void updatePromotionSpace(PromotionSpace promotionSpace);
}
