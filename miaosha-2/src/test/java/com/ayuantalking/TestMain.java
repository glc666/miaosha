package com.ayuantalking;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ayuantalking.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/28/0028 22:13
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestMain {

    private static String queue_test = "queue_test";

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;


    class Product{

        public void send(){
            User user = new User();
            user.setName("jack");
            user.setId(1);

            Map map = new HashMap();
            map.put("name","zhang3");
            map.put("user",user);
            jmsMessagingTemplate.convertAndSend(queue_test,map);
        }
    }


    @Test
    public void test(){
        Product product = new Product();
        product.send();
    }

}

@Component
@Slf4j
class Constumer{

    @JmsListener(destination = "queue_test" )
    public void reduce(Map message){
        log.info("message============"+message.size());
    }
}
