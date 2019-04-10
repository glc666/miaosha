package com.ayuantalking;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/28/0028 22:29
 * @Version 1.0
 */
public class MyMessage implements MessageCreator {

    @Override
    public Message createMessage(Session session) throws JMSException {
        TextMessage textMessage = new ActiveMQTextMessage();
        return null;
    }
}
