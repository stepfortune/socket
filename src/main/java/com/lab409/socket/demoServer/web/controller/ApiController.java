package com.lab409.socket.demoServer.web.controller;

import com.lab409.socket.demoServer.enums.SensorState;
import com.lab409.socket.demoServer.enums.SensorType;
import com.lab409.socket.demoServer.model.GroupDetail;
import com.lab409.socket.demoServer.model.Sensor;
import com.lab409.socket.demoServer.model.SensorGroup;
import com.lab409.socket.demoServer.utils.ClientUtil;
import com.lab409.socket.demoServer.utils.DataUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@EnableCaching
public class ApiController {

    @Autowired
    DataUtil dataUtil;

    @Autowired
    ClientUtil clientUtil;

    @Autowired
    AmqpTemplate rabbitTemplate;

    @GetMapping("/test")
    public Sensor test() {
        Sensor sensor = new Sensor();
        sensor.setType(SensorType.humidity);
        sensor.setDescr("sensor test");
        sensor.setId(Long.valueOf(1));
        sensor.setHost("localhost");
        sensor.setState(SensorState.online);
        return sensor;
    }

    private boolean check(SensorGroup group) {
        int sum = 0;
        for (GroupDetail detail : group.getGroupDetails()) {
            if (detail.getSensorNum() < 0) return false;
            sum += detail.getSensorNum();
            if (sum > 100) return false;
        }
        return sum != 0;
    }

    @CachePut(value = "groups", key = "100")
    @PostMapping("/addNewGroup")
    public List<SensorGroup> addNewGroup(@RequestBody SensorGroup group) {
        if(check(group)) {
            group.setCreateTime(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date())));
            dataUtil.groupMapper.insert(group);
            Sensor sensor = new Sensor();
            sensor.setGroupId(group.getId());
            sensor.setChangedTime(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date())));
            for (GroupDetail detail : group.getGroupDetails()) {
                detail.setGroupId(group.getId());
                dataUtil.detailMapper.insert(detail);
                for (Integer i = 0; i < detail.getSensorNum(); i++) {
                    sensor.setType(detail.getType());
                    dataUtil.sensorMapper.simplyInsert(sensor);
                }
            }
        }
        return dataUtil.groupMapper.getAll();
    }


    @Cacheable(value = "groups", key = "100")
    @GetMapping("/getAllGroup")
    public List<SensorGroup> getAllGroup() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(new Date()));
        List<SensorGroup> groups = dataUtil.groupMapper.getAll();
        System.out.println(dateFormat.format(new Date()));
        return groups;
    }

    @GetMapping("/getSensorsDividedByType")
    public Map<String, List<Sensor>> getSensorsDividedByType(Long id) {
        //后台创建client线程
        rabbitTemplate.convertAndSend("order", "create/" + id.toString());
        Map<String, List<Sensor>> map = new HashMap<>();
        for (SensorType type : SensorType.values()) {
            List<Sensor> sensors = dataUtil.sensorMapper.getManyByGroupIdAndType(Long.valueOf(id), type);
            map.put(type.name(), sensors);
        }
        return map;
    }


    @GetMapping("/getSensors")
    public List<Sensor> getSensors(Integer id) {
        return dataUtil.sensorMapper.getManyByGroupId(Long.valueOf(id));
    }

}