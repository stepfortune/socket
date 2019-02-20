package com.lab409.socket.demoServer.web.controller;

import com.lab409.socket.demoServer.utils.DataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RestController
@EnableScheduling
public class WebSocketController {

    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @Autowired
    AmqpTemplate rabbitTemplate;

    @Autowired
    DataUtil util;

    private Logger logger = LoggerFactory.getLogger(WebSocketController.class);


    @GetMapping("/wstest")
    public ModelAndView asdf(ModelAndView modelAndView) {
        modelAndView.setViewName("wstest");
        return modelAndView;
    }

    @MessageMapping("/send")
    //@SendToUser(value = "/topic/send",broadcast = false)
    @SendTo("/topic/send")
    public void send(SocketMessage msg) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        msg.date = df.format(new Date());
        logger.info(msg.toString());
        //return msg;
    }


    @MessageMapping("/order")
    public void updateMsg(String order) {
        rabbitTemplate.convertAndSend("exchange","topic.order", order);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(new Date());
        logger.info(date + " " + order);
    }


    @Scheduled(fixedRate = 1000)
    @SendTo("/topic/callback")
    public Object callback() {
        DateFormat df  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         return "callback";
    }

    @SubscribeMapping("/topic/send")
    public SocketMessage sub() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SocketMessage msg = new SocketMessage();
        msg.message = "thank you for subscribe me~";
        msg.date = df.format(new Date());
        return msg;
    }

}

class SocketMessage {
    public String message;
    public String date;

    @Override
    public String toString() {
        return message + " " + date;
    }
}