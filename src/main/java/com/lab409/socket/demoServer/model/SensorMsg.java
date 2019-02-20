package com.lab409.socket.demoServer.model;

import java.io.Serializable;
import java.util.Date;

public class SensorMsg implements Serializable {

    private Long id;

    private Long sensorId;

    private String msg;

    private Date sendTime;

    public SensorMsg() {}

    public SensorMsg(Long sensorId, String msg, Date sendTime) {
        this.sensorId = sensorId;
        this.msg = msg;
        this.sendTime = sendTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "id: " + id + "sensorId: " + sensorId + "msg: " + msg + "sendTime: " + sendTime;
    }
}
