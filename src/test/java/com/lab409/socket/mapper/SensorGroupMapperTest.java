package com.lab409.socket.mapper;

import com.lab409.socket.demoServer.mapper.SensorGroupMapper;
import com.lab409.socket.demoServer.model.GroupDetail;
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
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SensorGroupMapperTest {
   /* @Autowired
    SensorGroupMapper sensorGroupMapper;

    @Test
    public void insert() {
        SensorGroup config = new SensorGroup();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp timestamp = Timestamp.valueOf(format.format(new java.util.Date()));
        config.setCreateTime(timestamp);
        config.setCreateUser("admin");
        sensorGroupMapper.insert(config);
    }

    @Test
    public void queryOne() {
        SensorGroup config = sensorGroupMapper.getOneById(Long.valueOf(4));
        List<GroupDetail> details = config.getGroupDetails();
        List<Sensor> sensors = config.getSensors();
        System.out.println(config.getId() + " " +config.getCreateUser() +" "+ config.getCreateTime());
        for (GroupDetail detail :details){
            System.out.println(detail.getGroupId()+" "+detail.getType()+" "+detail.getSensorNum());
        }
        for (Sensor sensor : sensors) {
            //System.out.println(sensor.getId() + " " + sensor.getType() + " " +sensor.getLatestMsg());
            for(SensorMsg msg : sensor.getSensorMsgs()) {
                System.out.println(msg.getSensorId() + " " + msg.getMsg() +" "+ msg.getSendTime());
            }
        }


    }
*/
}
