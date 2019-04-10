package com.ayuantalking.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ayuantalking.domain.MiaoshaMessage;
import com.ayuantalking.domain.MiaoshaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.Message;
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
        //jmsMessagingTemplate.convertAndSend("my_msg", msg);

        MiaoshaUser user = new MiaoshaUser();
        user.setNickname("jack");
        MiaoshaMessage message = new MiaoshaMessage();
        message.setUser(user);
        String miaoshaMessage= JSON.toJSONString(user);
        jmsMessagingTemplate.convertAndSend("my_msg",miaoshaMessage);
        System.out.println("msg发送成功");
    }

    @RequestMapping("/sendMap")
    public void sendMap() {
        Map map = new HashMap();
        map.put("mobile", "13888888888");
        map.put("content", "王总喜提兰博基尼");
        jmsMessagingTemplate.convertAndSend("my_map", map);
        System.out.println("map发送成功");
    }
}