package com.ayuantalking.service.impl;

import com.ayuantalking.domain.MiaoshaOrder;
import com.ayuantalking.domain.MiaoshaUser;
import com.ayuantalking.domain.OrderInfo;
import com.ayuantalking.service.MiaoshaGoodsService;
import com.ayuantalking.service.MiaoshaOrderService;
import com.ayuantalking.service.MiaoshaService;
import com.ayuantalking.service.OrderService;
import com.ayuantalking.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.Date;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/27/0027 22:31
 * @Version 1.0
 */
@Service
public class MiaoshaServiceImpl implements MiaoshaService {

    @Autowired
    private MiaoshaGoodsService miaoshaGoodsService;

    @Autowired
    private MiaoshaOrderService miaoshaOrderService;

    @Autowired
    private OrderService orderService;

    @Transient
    public void createOrder(MiaoshaUser miaoshaUser, GoodsVo goodsVo) {


        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(miaoshaUser.getId());
        orderInfo.setGoodsId(goodsVo.getGoodsId());
        orderInfo.setDeliveryAddrId(1L);
        orderInfo.setGoodsName(goodsVo.getGoodsName());;
        orderInfo.setGoodsPrice(goodsVo.getGoodsPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setCreateDate(new Date());
        Long orderId = orderService.create(orderInfo);

        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setUserId(miaoshaUser.getId());
        miaoshaOrder.setGoodsId(goodsVo.getGoodsId());
        miaoshaOrder.setOrderId(orderId);
        miaoshaOrderService.createOrder(miaoshaOrder);
    }



    @Override
    public void miaosha(MiaoshaUser miaoshaUser, GoodsVo goodsVo) {
        // 减少库存
        miaoshaGoodsService.reduceStock(goodsVo.getGoodsId());

    }
}
