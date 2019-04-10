package com.ayuantalking.service.impl;

import com.ayuantalking.dao.GoodsOrderDao;
import com.ayuantalking.domain.MiaoshaOrder;
import com.ayuantalking.domain.MiaoshaUser;
import com.ayuantalking.domain.OrderInfo;
import com.ayuantalking.service.GoodsOrderService;
import com.ayuantalking.service.GoodsVoService;
import com.ayuantalking.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/28/0028 10:26
 * @Version 1.0
 */
@Service
public class GoodsOrderServiceImpl implements GoodsOrderService {

    @Autowired
    GoodsOrderDao goodsOrderDao;

    @Autowired
    GoodsVoService goodsVoService;

    @Override
    public MiaoshaOrder findGoodsOrderByUserIdAndGoodsId(Long userId, long goodsId) {
        return goodsOrderDao.findGoodsOrderByUserIdAndGoodsId(userId,goodsId);
    }

    @Transactional
    @Override
    public void domiaosha(MiaoshaUser miaoshaUser, GoodsVo goodsVo) {
        // 减库存 下订单
        int result = goodsVoService.reduceStock(goodsVo.getGoodsId());
        if(result > 0){
            // 下订单
            createOrder(miaoshaUser,goodsVo);
        }
    }

    public void createOrder(MiaoshaUser miaoshaUser, GoodsVo goodsVo){

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(miaoshaUser.getId());
        orderInfo.setGoodsId(goodsVo.getGoodsId());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsName(goodsVo.getGoodsName());
        orderInfo.setGoodsCount(0);
        orderInfo.setGoodsPrice(goodsVo.getGoodsPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setCreateDate(new Date());

        goodsOrderDao.addOrderInfo(orderInfo);

        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setGoodsId(goodsVo.getGoodsId());
        miaoshaOrder.setOrderId(orderInfo.getId());
        miaoshaOrder.setUserId(miaoshaUser.getId());
        goodsOrderDao.addMiaoshaoOrder(miaoshaOrder);
    }
}
