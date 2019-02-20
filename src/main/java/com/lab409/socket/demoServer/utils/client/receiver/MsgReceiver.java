package com.lab409.socket.demoServer.utils.client.receiver;


import com.lab409.socket.demoServer.model.SensorMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@EnableScheduling
@RabbitListener(queues = "msg")
public class MsgReceiver {

    private Logger logger = LoggerFactory.getLogger(MsgReceiver.class);
    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @RabbitHandler
    @SendTo("/topic/clientMsg")
    public Object sendMsg(String msg) {
        logger.info(Thread.currentThread().getId()+ " " + Thread.currentThread().getName());
        Random random = new Random();
        List<SensorMsg> msgList = new ArrayList<>();
        String[] strings = msg.split("/");
        if (strings.length == 2) {
            SensorMsg message= null;
            try {
                Long id = Long.valueOf(strings[0].substring(2));
                message = new SensorMsg();
                message.setMsg(strings[1] + " " + random.nextInt(100) + "%");
                message.setSensorId(id);
                message.setSendTime(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            if (message != null)
                msgList.add(message);
            if (!msgList.isEmpty())
                messagingTemplate.convertAndSend("/topic/clientMsg", msgList);
        } else {
            System.out.println(msg);
            System.out.println(strings.length);
        }
        return "clientMsg";
    }
}