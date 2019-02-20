package com.lab409.socket.mapper;

import com.lab409.socket.demoServer.enums.SensorType;
import com.lab409.socket.demoServer.mapper.GroupDetailMapper;
import com.lab409.socket.demoServer.model.GroupDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GroupDetailMapperTest {
    @Autowired
    GroupDetailMapper detailMapper;

    @Test
    public void insert() {
        GroupDetail groupDetail = new GroupDetail();
        groupDetail.setGroupId(Long.valueOf(4));
        groupDetail.setType(SensorType.humidity);
        groupDetail.setSensorNum(Long.valueOf(2));
        detailMapper.insert(groupDetail);
    }

    @Test
    public void queryOne(){
        GroupDetail detail = detailMapper.getOneByIdAndType(Long.valueOf(4),SensorType.thunder);
        System.out.println(detail.getSensorNum());
    }

    @Test
    public void queryMany() {
        List<GroupDetail> details = detailMapper.getManyByGroupId(Long.valueOf(4));
        for (GroupDetail detail : details) {
            System.out.println(detail.getGroupId() + " " + detail.getType()+ " "+ detail.getSensorNum());
        }
    }
}
