package com.soldier.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soldier.dao.PromotionAdMapper;
import com.soldier.domian.PromotionAd;
import com.soldier.domian.PromotionAdVO;
import com.soldier.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PromotionAdServiceImpl implements PromotionAdService {

    @Autowired
    private PromotionAdMapper promotionAdMapper;

    @Override
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVO promotionAdVO) {

        PageHelper.startPage(promotionAdVO.getCurrentPage(),promotionAdVO.getPageSize());
        List<PromotionAd> list = promotionAdMapper.findAllPromotionAdByPage();

        PageInfo<PromotionAd> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @Override
    public void savePromotionAd(PromotionAd promotionAd) {

        Date date = new Date();
        promotionAd.setCreateTime(date);
        promotionAd.setUpdateTime(date);

        promotionAdMapper.savePromotionAd(promotionAd);
    }

    @Override
    public void updatePromotionAd(PromotionAd promotionAd) {

        Date date = new Date();
        promotionAd.setUpdateTime(date);

        promotionAdMapper.updatePromotionAd(promotionAd);
    }

    @Override
    public PromotionAd findPromotionAdById(int id) {

        PromotionAd promotionAd = promotionAdMapper.findPromotionAdById(id);
        return promotionAd;
    }

    @Override
    public void updatePromotionAdStatus(int id,int status) {

        PromotionAd promotionAd = new PromotionAd();
        promotionAd.setId(id);
        promotionAd.setStatus(status);

        promotionAdMapper.updatePromotionAdStatus(promotionAd);
    }
}
