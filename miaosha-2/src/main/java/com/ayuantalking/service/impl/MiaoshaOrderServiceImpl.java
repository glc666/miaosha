package com.ayuantalking.service.impl;

import com.ayuantalking.dao.MiaoshaOrderDao;
import com.ayuantalking.domain.MiaoshaOrder;
import com.ayuantalking.service.MiaoshaOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author: ayuantalking
 * @Date: 2019/3/27/0027 21:51
 * @Version 1.0
 */
@Service
public class MiaoshaOrderServiceImpl implements MiaoshaOrderService {

    @Autowired
    private MiaoshaOrderDao miaoshaOrderDao;

    @Autowired
    private MiaoshaOrderService miaoshaOrderService;

    @Override
    public MiaoshaOrder findMiaoshaOrderByUserIdAndGoodsId(Long userId, long goodsId) {
       // return miaoshaOrderDao.findMiaoshaOrderByUserIdAndGoodsId(userId,goodsId);
        return null;
    }

    @Override
    public void createOrder(MiaoshaOrder miaoshaOrder) {
        miaoshaOrderService.createOrder(miaoshaOrder);
    }

}
