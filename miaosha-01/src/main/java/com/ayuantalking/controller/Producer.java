package com.ayuantalking.controller;

import com.ayuantalking.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Producer {
    //注入jsmtemplate
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @RequestMapping("/sendMsg")
    public void sendMsg(String msg) {

        GoodsVo goodsVo = new GoodsVo();
        goodsVo.setGoodsId(123L);
        GenericMessage<GoodsVo> genericMessage = new GenericMessage<GoodsVo>(goodsVo);

        //jmsMessagingTemplate.convertAndSend("my_msg", genericMessage);
        jmsMessagingTemplate.send("my_msg", genericMessage);
        System.out.println("msg发送成功");
    }

    @RequestMapping("/sendMap")
    public void sendMap() {
        Map map = new HashMap();
        map.put("userId", 1);
        map.put("goodsId", 1);
        jmsMessagingTemplate.convertAndSend("do_miaosha", map);
        System.out.println("map发送成功");
    }
}