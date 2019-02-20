package com.lab409.socket.demoServer.mapper;

import com.lab409.socket.demoServer.enums.SensorState;
import com.lab409.socket.demoServer.enums.SensorType;
import com.lab409.socket.demoServer.model.Sensor;
import com.lab409.socket.demoServer.model.SensorGroup;
import org.apache.ibatis.annotations.*;

import java.util.List;

// checked

public interface SensorMapper {

    @Select("select * from sensor where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "groupId", column = "group_id"),
            @Result(property = "type", column = "type", javaType = SensorType.class),
            @Result(property = "host", column = "host"),
            @Result(property = "port", column = "port"),
            @Result(property = "state", column = "state", javaType = SensorState.class),
            @Result(property = "descr", column = "descr"),
            @Result(property = "latestMsg", column = "id", javaType = String.class,
                    one = @One(select = "com.lab409.socket.demoServer.mapper.SensorMsgMapper.getLatestOneBySensorId")),
            @Result(property = "interval", column = "interval"),
            @Result(property = "changedTime", column = "time"),
            @Result(property = "msgNum", column = "id", javaType = Long.class,
                    one = @One(select = "com.lab409.socket.demoServer.mapper.SensorMsgMapper.getNumBySensorId"))

    })
    Sensor getOneById(@Param("id") Long id);

    @Select("select * from sensor where group_id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "groupId", column = "group_id"),
            @Result(property = "type", column = "type", javaType = SensorType.class),
            @Result(property = "host", column = "host"),
            @Result(property = "port", column = "port"),
            @Result(property = "state", column = "state", javaType = SensorState.class),
            @Result(property = "descr", column = "descr"),
            @Result(property = "latestMsg", column = "id", javaType = String.class,
                    one = @One(select = "com.lab409.socket.demoServer.mapper.SensorMsgMapper.getLatestOneBySensorId")),
            @Result(property = "interval", column = "interval"),
            @Result(property = "changedTime", column = "time"),
            @Result(property = "msgNum", column = "id", javaType = Long.class,
                    one = @One(select = "com.lab409.socket.demoServer.mapper.SensorMsgMapper.getNumBySensorId"))
    })
    List<Sensor> getManyByGroupId(@Param("id") Long id);

    @Select("select * from sensor where group_id=#{id} and type=#{type}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "groupId", column = "group_id"),
            @Result(property = "type", column = "type", javaType = SensorType.class),
            @Result(property = "host", column = "host"),
            @Result(property = "port", column = "port"),
            @Result(property = "state", column = "state", javaType = SensorState.class),
            @Result(property = "descr", column = "descr"),
            @Result(property = "latestMsg", column = "id", javaType = String.class,
                    one = @One(select = "com.lab409.socket.demoServer.mapper.SensorMsgMapper.getLatestOneBySensorId")),
            @Result(property = "interval", column = "interval"),
            @Result(property = "changedTime", column = "time"),
            @Result(property = "msgNum", column = "id", javaType = Long.class,
                    one = @One(select = "com.lab409.socket.demoServer.mapper.SensorMsgMapper.getNumBySensorId"))

    })
    List<Sensor> getManyByGroupIdAndType(@Param("id") Long id, @Param("type") SensorType type);

    
    @Insert("insert into sensor(group_id, type, descr ,host, port, msg, state, time, `interval`) " +
            "VALUES(#{groupId}, #{type}, #{descr}, #{host}, #{port}, #{latestMsg}, #{state}, #{changedTime}, #{interval})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Sensor sensor);

    @Insert("insert into sensor(group_id, type) values(#{groupId}, #{type})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void simplyInsert(Sensor sensor);


    @Update("update sensor set type=#{type}, descr=#{descr}, host=#{host}, " +
            "port=#{port}, state=#{state}, msg=#{latestMsg}, interval=#{interval} WHERE id=#{id}")
    void update(Sensor sensor);

    @Delete("deleted from sensor where id=#{id}")
    void delete(Long id);
}
