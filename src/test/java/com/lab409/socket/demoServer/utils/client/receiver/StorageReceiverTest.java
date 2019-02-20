package com.lab409.socket.demoServer.utils.client.receiver;

import com.datastax.driver.core.Session;
import com.lab409.socket.demoServer.model.SensorMsg;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StorageReceiverTest {

    @Autowired
    RabbitTemplate template;

    @Autowired
    Session session;

    @Autowired
    StorageReceiver receiver;

    @Test
    public void insertExpectSuccess() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SensorMsg msg = new SensorMsg();
        msg.setId(Long.valueOf(4));
        msg.setSendTime(Timestamp.valueOf(format.format(new java.util.Date())));
        msg.setMsg("5.3 400 正");
        boolean success = receiver.insert(msg);
        Assert.assertTrue(success);

        Thread.sleep(1000);

        msg.setId(Long.valueOf(3));
        msg.setMsg("5.4 401 负");
        msg.setSendTime(Timestamp.valueOf(format.format(new java.util.Date())));
        success = receiver.insert(msg);
        Assert.assertTrue(success);

        Thread.sleep(1000);

        msg.setId(Long.valueOf(5));
        msg.setMsg("5.2 401 正");
        msg.setSendTime(Timestamp.valueOf(format.format(new java.util.Date())));
        success = receiver.insert(msg);
        Assert.assertTrue(success);

        Thread.sleep(1000);

        msg.setId(Long.valueOf(6));
        msg.setMsg("0.1 402 正");
        msg.setSendTime(Timestamp.valueOf(format.format(new java.util.Date())));
        success = receiver.insert(msg);
        Assert.assertTrue(success);

        Thread.sleep(1000);

        msg.setId(Long.valueOf(7));
        msg.setMsg("0.5 403 正");
        msg.setSendTime(Timestamp.valueOf(format.format(new java.util.Date())));
        success = receiver.insert(msg);
        Assert.assertTrue(success);

        Thread.sleep(1000);

        msg.setId(Long.valueOf(8));
        msg.setMsg("8.9 404 正");
        msg.setSendTime(Timestamp.valueOf(format.format(new java.util.Date())));
        success = receiver.insert(msg);
        Assert.assertTrue(success);

        Thread.sleep(1000);

        msg.setId(Long.valueOf(9));
        msg.setMsg("9.9 405 正");
        msg.setSendTime(Timestamp.valueOf(format.format(new java.util.Date())));
        success = receiver.insert(msg);
        Assert.assertTrue(success);

        Thread.sleep(1000);

        msg.setId(Long.valueOf(10));
        msg.setMsg("5.3 18 正");
        msg.setSendTime(Timestamp.valueOf(format.format(new java.util.Date())));
        success = receiver.insert(msg);
        Assert.assertTrue(success);

        Thread.sleep(1000);

        msg.setId(Long.valueOf(11));
        msg.setMsg("5.3 20 正");
        msg.setSendTime(Timestamp.valueOf(format.format(new java.util.Date())));
        success = receiver.insert(msg);
        Assert.assertTrue(success);

        Thread.sleep(1000);

        msg.setId(Long.valueOf(12));
        msg.setMsg("5.3 930 正");
        msg.setSendTime(Timestamp.valueOf(format.format(new java.util.Date())));
        success = receiver.insert(msg);
        Assert.assertTrue(success);

        Thread.sleep(1000);

        msg.setId(Long.valueOf(13));
        msg.setMsg("5.3 941 正");
        msg.setSendTime(Timestamp.valueOf(format.format(new java.util.Date())));
        success = receiver.insert(msg);
        Assert.assertTrue(success);

        Thread.sleep(1000);

        msg.setId(Long.valueOf(14));
        msg.setMsg("0.1 402 正");
        msg.setSendTime(Timestamp.valueOf(format.format(new java.util.Date())));
        success = receiver.insert(msg);
        Assert.assertTrue(success);
    }

    @Test
    public void insertExpectFailure() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SensorMsg msg = new SensorMsg();
        msg.setId(Long.valueOf(4));
        msg.setSendTime(Timestamp.valueOf(format.format(new java.util.Date())));
        msg.setMsg("5.3 400 W");
        boolean failure = receiver.insert(msg);
        Assert.assertFalse(failure);

        Thread.sleep(1000);

        msg.setId(Long.valueOf(3));
        msg.setMsg("5.2 401 非负");
        msg.setSendTime(Timestamp.valueOf(format.format(new java.util.Date())));
        failure = receiver.insert(msg);
        Assert.assertFalse(failure);

        msg.setId(Long.valueOf(3));
        msg.setMsg("0.003 401 正");
        msg.setSendTime(Timestamp.valueOf(format.format(new java.util.Date())));
        failure = receiver.insert(msg);
        Assert.assertFalse(failure);

        Thread.sleep(1000);

        msg.setId(Long.valueOf(3));
        msg.setMsg("12.3 401 正");
        msg.setSendTime(Timestamp.valueOf(format.format(new java.util.Date())));
        failure = receiver.insert(msg);
        Assert.assertFalse(failure);

        Thread.sleep(1000);

        msg.setId(Long.valueOf(3));
        msg.setMsg("5.3 2 正");
        msg.setSendTime(Timestamp.valueOf(format.format(new java.util.Date())));
        failure = receiver.insert(msg);
        Assert.assertFalse(failure);

        Thread.sleep(1000);

        msg.setId(Long.valueOf(3));
        msg.setMsg("5.3 1000 正");
        msg.setSendTime(Timestamp.valueOf(format.format(new java.util.Date())));
        failure = receiver.insert(msg);
        Assert.assertFalse(failure);
    }
}