package com.lab409.socket.mapper;

import com.lab409.socket.demoServer.enums.SensorType;
import com.lab409.socket.demoServer.mapper.SensorMapper;
import com.lab409.socket.demoServer.model.Sensor;
import com.lab409.socket.demoServer.model.SensorGroup;
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
public class SensorMapperTest {
    /*@Autowired
    SensorMapper sensorMapper;

    @Test
    public void insert() {
        Sensor sensor = new Sensor();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sensor.setLatestMsg("tomorrow has thunder");
        sensor.setChangedTime(Timestamp.valueOf(format.format(new Date())));
        sensor.setType(SensorType.humidity);
        SensorGroup config = new SensorGroup();
        config.setId(Long.valueOf(4));
        sensor.setSensorGroup(config);
        sensorMapper.insert(sensor);
    }

    @Test
    public void queryOne() {
        Sensor sensor = sensorMapper.getOneById(Long.valueOf(9));
        System.out.println(sensor.getId() + " " + sensor.getType() + " " + sensor.getLatestMsg());
        for (SensorMsg msg : sensor.getSensorMsgs()) {
            System.out.println(msg.getSensorId() + " " + msg.getMsg() + " " + msg.getSendTime());
        }
    }

    @Test
    public void queryMany() {
        List<Sensor> sensors = sensorMapper.getManyByGroupId(Long.valueOf(4));
        for (Sensor sensor : sensors) {
            System.out.println(sensor.getId() + " " + sensor.getType() + " " + sensor.getLatestMsg());
            for (SensorMsg msg : sensor.getSensorMsgs()) {
                System.out.println(msg.getSensorId() + " " + msg.getMsg() + " " + msg.getSendTime());
            }
        }
    }

    @Test
    public void update() {
        Sensor sensor = new Sensor();
        sensor.setLatestMsg("sent by huzehao");
        sensor.setId(Long.valueOf(1));
        sensorMapper.update(sensor);
    }*/
}
