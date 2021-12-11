package com.soldier.service.impl;

import com.soldier.dao.PromotionSpaceMapper;
import com.soldier.domian.PromotionSpace;
import com.soldier.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PromotionSpaceServiceImpl implements PromotionSpaceService {

    @Autowired
    private PromotionSpaceMapper promotionSpaceMapper;
    @Override
    public List<PromotionSpace> findAllPromotionSpace() {

        List<PromotionSpace> list = promotionSpaceMapper.findAllPromotionSpace();

        return list;
    }

    @Override
    public void savePromotionSpace(PromotionSpace promotionSpace) {

        // 1.封装数据
        promotionSpace.setSpaceKey(UUID.randomUUID().toString());
        Date date = new Date();
        promotionSpace.setCreateTime(date);
        promotionSpace.setUpdateTime(date);
        promotionSpace.setIsDel(0);

        // 2.调用mapper方法
        promotionSpaceMapper.savePromotionSpace(promotionSpace);
    }

    @Override
    public PromotionSpace findPromotionSpaceById(int id) {

        PromotionSpace promotionSpace = promotionSpaceMapper.findPromotionSpaceById(id);
        return promotionSpace;
    }

    @Override
    public void updatePromotionSpace(PromotionSpace promotionSpace) {

        promotionSpace.setUpdateTime(new Date());

        promotionSpaceMapper.updatePromotionSpace(promotionSpace);
    }
}
