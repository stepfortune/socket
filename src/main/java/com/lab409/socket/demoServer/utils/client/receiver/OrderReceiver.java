package com.lab409.socket.demoServer.utils.client.receiver;

import com.lab409.socket.demoServer.enums.SensorState;
import com.lab409.socket.demoServer.utils.ClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "order")
public class OrderReceiver {

    @Autowired
    ClientUtil clientUtil;

    private Logger logger = LoggerFactory.getLogger(OrderReceiver.class);

    @RabbitHandler
    public void executeOrder(String order) {
        String[] strings = order.split("/");
        Long id = Long.valueOf(strings[1]);
        switch (strings[0]) {
            case "state":
                logger.info("a new state order: " + order);
                SensorState sensorState = SensorState.online;
                if (strings[2].equals("false"))
                    sensorState = SensorState.offline;
                clientUtil.updateClientState(id, sensorState);
                break;
            case "msg":
                logger.info("a new msg order: " + order);
                clientUtil.updateClientMsg(id, Long.valueOf(strings[2]), strings[3]);
                break;
            case "create":
                logger.info("clients of group " + strings[1] + " will be created");
                clientUtil.createClients(Long.valueOf(strings[1]));
                break;
        }
    }
}
