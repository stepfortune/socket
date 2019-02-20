package com.lab409.socket.mapper;

import com.lab409.socket.demoServer.mapper.SensorMsgMapper;
import com.lab409.socket.demoServer.model.SensorMsg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SensorMsgMapperTest {
    @Autowired
    SensorMsgMapper sensorMsgMapper;

    @Test
    public void insert(){
        SensorMsg msg = new SensorMsg();
        msg.setSendTime(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        msg.setSensorId(Long.valueOf(12));
        msg.setMsg("humidity high today");
        sensorMsgMapper.insert(msg);

    }

    @Test
    public void queryOne() {
        SensorMsg sensorMsg = sensorMsgMapper.getOneById(Long.valueOf(1));
        System.out.println(sensorMsg.getId() + " " +sensorMsg.getMsg());

    }
    @Test
    public void queryAll() {
        List<SensorMsg> msgs = sensorMsgMapper.getManyBySensorId(Long.valueOf(9));
        for (SensorMsg msg : msgs){
            System.out.println(msg.getId() + " " +msg.getMsg());
        }
    }
}
