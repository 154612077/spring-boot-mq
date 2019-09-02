package com.rabbit.demo;

import com.rabbit.demo.rabbit.MsgProducer;
import com.rabbit.demo.rabbit.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class RabbitmqApplicationTests {


    @Autowired
    private Sender sender;
    @Autowired
    private MsgProducer msgProducer;

    @GetMapping("/test")
    public void mqTest(@RequestParam("msg") String msg){
        sender.send(msg);
    }


    @GetMapping("/testAll")
    public void testAll(@RequestParam("msg") String msg){
        msgProducer.sendAll(msg);
    }
    @GetMapping("/testA")
    public void testA(@RequestParam("msg") String msg){
        msgProducer.sendMsg(msg);
    }
    @GetMapping("/testB")
    public void testB(@RequestParam("msg") String msg){
        User user = new User();
        user.setDate(new Date());
        user.setId(1l);
        user.setName(msg);
        msgProducer.sendMsgDto(user);
    }
}
