package com.soldier.service;

import com.github.pagehelper.PageInfo;
import com.soldier.domian.PromotionAd;
import com.soldier.domian.PromotionAdVO;

import java.util.List;

public interface PromotionAdService {

    /*
        分页查询广告信息
     */
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVO promotionAdVO);

    /*
        添加广告
     */
    public void savePromotionAd(PromotionAd promotionAd);

    /*
        修改广告
     */
    public void updatePromotionAd(PromotionAd promotionAd);

    /*
        回显广告信息
     */
    public PromotionAd findPromotionAdById(int id);

    /*
        修改广告状态
     */
    public void updatePromotionAdStatus(int id,int status);
}
