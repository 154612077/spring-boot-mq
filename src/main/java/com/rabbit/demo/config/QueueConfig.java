package com.rabbit.demo.config;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    /**
     * 创建消息队列
     * @return
     */
    @Bean
    public Queue createQueue(){
        return new Queue("Queue");
    }

}
