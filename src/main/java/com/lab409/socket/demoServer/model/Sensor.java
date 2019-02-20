package com.lab409.socket.demoServer.model;

import com.lab409.socket.demoServer.enums.SensorState;
import com.lab409.socket.demoServer.enums.SensorType;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Sensor implements Serializable {
    private Long id;
    private Long groupId;
    private SensorType type;
    private String descr;
    private String host;
    private String port;
    private String latestMsg;
    private SensorState state;
    private Timestamp changedTime;
    private Long interval;
    private Long msgNum;


    public Sensor() {
    }

    public Sensor(Sensor sensor) {
        this.id = sensor.id;
        this.groupId = sensor.groupId;
        this.type = sensor.type;
        this.host = sensor.host;
        this.port = sensor.port;
        this.latestMsg = sensor.latestMsg;
        this.state = sensor.state;
        this.descr = sensor.descr;
        this.changedTime = sensor.changedTime;
        this.msgNum = sensor.msgNum;
        this.interval = sensor.interval;
    }

    public Sensor(Long id, Long groupId, SensorType type, String descr, String host, String port, String latestMsg, SensorState state, Timestamp changedTime, Long interval, Long msgNum) {
        this.id = id;
        this.groupId = groupId;
        this.type = type;
        this.descr = descr;
        this.host = host;
        this.port = port;
        this.latestMsg = latestMsg;
        this.state = state;
        this.changedTime = changedTime;
        this.interval = interval;
        this.msgNum = msgNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public SensorType getType() {
        return type;
    }

    public void setType(SensorType type) {
        this.type = type;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getLatestMsg() {
        return latestMsg;
    }

    public void setLatestMsg(String latestMsg) {
        this.latestMsg = latestMsg;
    }

    public SensorState getState() {
        return state;
    }

    public void setState(SensorState state) {
        this.state = state;
    }

    public Date getChangedTime() {
        return changedTime;
    }

    public void setChangedTime(Timestamp changedTime) {
        this.changedTime = changedTime;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getMsgNum() {
        return msgNum;
    }

    public void setMsgNum(Long msgNum) {
        this.msgNum = msgNum;
    }

    public Long getInterval() {
        return interval;
    }

    public void setInterval(Long interval) {
        this.interval = interval;
    }

    @Override
    public String toString() {
        return id + " " + type.name() + " " + descr + " " + host + " " + port + " " + latestMsg + " " + msgNum + " " + state + " " + changedTime + " " + interval;
    }
}
