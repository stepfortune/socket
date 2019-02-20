package com.lab409.socket.demoServer.model;

import com.lab409.socket.demoServer.enums.SensorType;

public class GroupDetail {
    private Long groupId;
    private SensorType type;
    private Long sensorNum;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public SensorType getType() {
        return type;
    }

    public void setType(SensorType type) {
        this.type = type;
    }

    public Long getSensorNum() {
        return sensorNum;
    }

    public void setSensorNum(Long sensorNum) {
        this.sensorNum = sensorNum;
    }
}
