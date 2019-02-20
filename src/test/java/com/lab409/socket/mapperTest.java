package com.lab409.socket;

import com.lab409.socket.demoServer.enums.SensorType;
import com.lab409.socket.demoServer.mapper.*;
import com.lab409.socket.demoServer.model.*;
import com.lab409.socket.demoServer.utils.DataUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class mapperTest {
    @Autowired
    SensorMapper sensorMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    SensorMsgMapper msgMapper;

    @Autowired
    SensorGroupMapper groupMapper;

    @Autowired
    GroupDetailMapper detailMapper;

    @Autowired
    DataUtil util;

    @Test
    public void insertUser() {
        //第一次插入
        User user = new User();
        user.setName("huzehao");
        user.setPwd("123456");
        userMapper.insert(user);
        User userFromDb = userMapper.getOne(Long.valueOf(1));
        Assert.assertEquals(user.getId(), Long.valueOf(1)); //插入后, 原对象会得到自增ID
        Assert.assertNotNull(userFromDb);
        Assert.assertEquals(userFromDb.getId(), Long.valueOf(1));
        Assert.assertEquals(userFromDb.getName(), "huzehao");
        Assert.assertEquals(userFromDb.getPwd(), "123456");
    }

    @Test
    public void insertGroup() {
        SensorGroup sensorGroup = new SensorGroup();
        sensorGroup.setCreateUser("huzehao");
        sensorGroup.setName("group1");
        groupMapper.insert(sensorGroup);
        Assert.assertEquals(sensorGroup.getId(), Long.valueOf(1));
        SensorGroup sensorGroupFromDb = groupMapper.getOneById(Long.valueOf(1));
        Assert.assertEquals(sensorGroupFromDb.getId(), Long.valueOf(1));
        Assert.assertEquals(sensorGroupFromDb.getCreateUser(), "huzehao");
        Assert.assertEquals(sensorGroupFromDb.getName(), "group1");
        Assert.assertTrue(sensorGroupFromDb.getGroupDetails().isEmpty());
    }

    @Test
    public void insertDetail() {
        GroupDetail detail = new GroupDetail();
        detail.setGroupId(Long.valueOf(1));
        detail.setSensorNum(Long.valueOf(2));
        detail.setType(SensorType.thunder);
        detailMapper.insert(detail);
        detail.setType(SensorType.humidity);
        detailMapper.insert(detail);
        detail.setType(SensorType.temperature);
        detailMapper.insert(detail);
        detail.setType(SensorType.pressure);
        detailMapper.insert(detail);

        GroupDetail detail1 = detailMapper.getOneByIdAndType(Long.valueOf(1), SensorType.temperature);
        Assert.assertEquals(detail1.getSensorNum(), Long.valueOf(2));
        GroupDetail detail2 = detailMapper.getOneByIdAndType(Long.valueOf(1), SensorType.pressure);
        Assert.assertEquals(detail2.getSensorNum(), Long.valueOf(2));
        GroupDetail detail3 = detailMapper.getOneByIdAndType(Long.valueOf(1), SensorType.humidity);
        Assert.assertEquals(detail3.getSensorNum(), Long.valueOf(2));
        GroupDetail detail4 = detailMapper.getOneByIdAndType(Long.valueOf(1), SensorType.thunder);
        Assert.assertEquals(detail4.getSensorNum(), Long.valueOf(2));

        List<GroupDetail> detailsFromDb = detailMapper.getManyByGroupId(Long.valueOf(1));
        SensorGroup sensorGroupFromDb = groupMapper.getOneById(Long.valueOf(1));
        List<GroupDetail> detailsFromGroup = sensorGroupFromDb.getGroupDetails();

        for (int i = 0; i < 2; i++) {
            List<GroupDetail> tmp;
            if (i == 0) {
                tmp = detailsFromDb;
            } else {
                tmp = detailsFromGroup;
            }
            for (SensorType type : SensorType.values()) {
                Boolean found = false;
                for (GroupDetail d : tmp) {
                    if (d.getType().equals(type)) {
                        Assert.assertEquals(d.getSensorNum(), Long.valueOf(2));
                        found = true;
                    }
                }
                Assert.assertTrue(found);
            }
        }
    }

    @Test
    public void insertSensor() {
        Sensor sensor = new Sensor();
        sensor.setGroupId(Long.valueOf(2));
        sensor.setInterval(Long.valueOf(1000));
        sensor.setType(SensorType.thunder);
        sensor.setDescr("made in china");
        sensorMapper.insert(sensor);
        sensorMapper.insert(sensor);
        sensor.setType(SensorType.humidity);
        sensorMapper.insert(sensor);
        sensorMapper.insert(sensor);
        sensor.setType(SensorType.pressure);
        sensorMapper.insert(sensor);
        sensorMapper.insert(sensor);
        sensor.setType(SensorType.temperature);
        sensorMapper.insert(sensor);
        sensorMapper.insert(sensor);
    }


    @Test
    public void insertMsg() {
        SensorMsg msg = new SensorMsg();
        for (int i = 9; i < 17; i++) {
            msg.setSensorId(Long.valueOf(i));
            msg.setMsg("Hello World " + i + " times");
            msg.setSendTime(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
            msgMapper.insert(msg);
            msg.setSendTime(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
            msgMapper.insert(msg);
        }
    }

    @Test
    public void querySensor() {
        List<Sensor> sensors = sensorMapper.getManyByGroupId(Long.valueOf(1));
        for (Sensor sensor : sensors) {
            System.out.println(sensor);
        }
    }

    @Test
    public void queryGroups_Sensors_Msgs() {
        List<SensorGroup> groups = groupMapper.getAll();
        for (SensorGroup g : groups) {
            System.out.println(g);
            for (GroupDetail detail : g.getGroupDetails()) {
                System.out.println(detail.getType() + " " + detail.getSensorNum());
            }
            for (Sensor sensor : g.getSensors()) {
                System.out.println(sensor);
            }
        }
    }


    @Test
    public void insertAll() {
        insertGroup();
        insertDetail();
        insertSensor();
        insertMsg();
    }

    @Test
    public void querySensorByGroupIdAndType() {
        List<Sensor> list = sensorMapper.getManyByGroupIdAndType(Long.valueOf(1), SensorType.temperature);
        for (Sensor sensor : list) {
            System.out.println(sensor);
        }
    }

}
