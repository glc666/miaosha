package com.ayuantalking.controller;


import com.ayuantalking.domain.MiaoshaMessage;
import com.ayuantalking.domain.MiaoshaUser;
import com.ayuantalking.service.GoodsOrderService;
import com.ayuantalking.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class Consumer {

    @Autowired
    private GoodsOrderService goodsOrderService;

    @JmsListener(destination = "domiaosha")
    public void readMsg(Message<MiaoshaMessage> message) {
        GoodsVo goodsVo = message.getPayload().getGoodsVo();
        MiaoshaUser miaoshauser = message.getPayload().getUser();
        log.info("goodsVo:{},miaoshauser:{}",goodsVo,miaoshauser);
        goodsOrderService.domiaosha(miaoshauser,goodsVo);
    }

    @JmsListener(destination = "do_miaosha")
    public void readMap(Map map) {

    }
}