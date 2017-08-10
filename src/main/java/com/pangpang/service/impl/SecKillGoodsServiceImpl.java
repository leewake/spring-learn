package com.pangpang.service.impl;

import com.pangpang.domain.SecKill.SecKillGoods;
import com.pangpang.domain.SecKill.SecKillOrder;
import com.pangpang.repository.SecKillGoodsRepository;
import com.pangpang.repository.SecKillOrderRepository;
import com.pangpang.service.SecKillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by leewake on 2017/8/10 0010.
 */

@Service
public class SecKillGoodsServiceImpl implements SecKillGoodsService {

    @Autowired
    private SecKillGoodsRepository secKillGoodsRepository;

    @Autowired
    private SecKillOrderRepository secKillOrderRepository;

    @PostConstruct
    public void initSecKillEntity(){
        secKillGoodsRepository.deleteAll();
        secKillOrderRepository.deleteAll();
        SecKillGoods secKillGoods = new SecKillGoods();
        secKillGoods.setId("123456");
        secKillGoods.setGoodsName("秒杀产品");
        secKillGoods.setRemainNum(10);
        secKillGoodsRepository.save(secKillGoods);
    }

    @Override
    public void generateOrder(String consumer, String goodsId, Integer num) {

        secKillOrderRepository.save(new SecKillOrder(consumer, goodsId, num));

    }

}
