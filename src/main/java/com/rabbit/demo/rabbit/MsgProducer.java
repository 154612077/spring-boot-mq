package com.rabbit.demo.rabbit;

import com.rabbit.demo.config.RabbitConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MsgProducer implements RabbitTemplate.ConfirmCallback ,RabbitTemplate.ReturnCallback{

    //由于rabbitTemplate的scope属性设置为ConfigurableBeanFactory.SCOPE_PROTOTYPE，所以不能自动注入
    private RabbitTemplate rabbitTemplate;
    /**
     * 构造方法注入rabbitTemplate
     */

    @Autowired
    public MsgProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this); //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
        rabbitTemplate.setReturnCallback(this);
    }

    public void sendMsg(String content) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        //把消息放入ROUTINGKEY_A对应的队列当中去，对应的是队列A
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_A, RabbitConfig.ROUTINGKEY_A, content, correlationId);
    }
    public void sendMsgDto(User user) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_A, RabbitConfig.ROUTINGKEY_B, user, correlationId);
    }
    public void sendAll(String content) {
        rabbitTemplate.convertAndSend(RabbitConfig.FANOUT_EXCHANGE,"", content);
    }
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String s) {
        System.out.printf(" 回调id:" + correlationData);
        if (ack) {
            System.out.printf("消息成功消费");
        } else {
            System.out.printf("消息消费失败" +s);
        }

    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.printf(message.toString());
        System.out.printf(i+"");
        System.out.printf(s);
        System.out.printf(s1);
        System.out.printf(s2);
    }
}

