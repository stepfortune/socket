package com.lab409.socket.demoServer.mapper;

import com.lab409.socket.demoServer.enums.SensorType;
import com.lab409.socket.demoServer.model.GroupDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

//checked

public interface GroupDetailMapper {
    @Insert("insert into group_detail(group_id, type, num) " +
            "values(#{groupId}, #{type}, #{sensorNum})")
    void insert(GroupDetail groupDetail);

    @Select("select * from group_detail where group_id = #{id}")
    @Results({
            @Result(property = "groupId", column = "group_id"),
            @Result(property = "type", column = "type"),
            @Result(property = "sensorNum", column = "num")
    })
    List<GroupDetail> getManyByGroupId(@Param("id") Long id);

    @Select("select  * from group_detail where group_id = #{id} and type = #{type}")
    @Results({
            @Result(property = "groupId", column = "group_id"),
            @Result(property = "type", column = "type"),
            @Result(property = "sensorNum", column = "num")
    })
    GroupDetail getOneByIdAndType(@Param("id") Long id, @Param("type") SensorType type);
}