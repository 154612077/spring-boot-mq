package com.rabbit.demo.rabbit;


import com.rabbit.demo.config.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitConfig.QUEUE_B)
public class MsgReceiver2 {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//    @RabbitHandler
//    public void processDto(User user) {
//        logger.info("接收处理队列B当中的消息： " + user);
//    }
//    @RabbitHandler
//    public void process(String msg) {
//        logger.info("接收处理队列B当中的消息： " + msg);
//    }
}
