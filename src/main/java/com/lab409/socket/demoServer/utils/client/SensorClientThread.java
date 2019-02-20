package com.lab409.socket.demoServer.utils.client;

import com.lab409.socket.demoServer.enums.SensorState;
import com.lab409.socket.demoServer.model.Sensor;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SensorClientThread implements Runnable {
    private volatile Sensor sensor;
    public static final Integer standardSleepTime = 1000;
    private Socket socket = null;
    private volatile SensorState threadState = SensorState.using;

    @Override
    public void run() {
        OutputStream os = null;
        DataOutputStream dos = null;
        try {
            socket = new Socket("127.0.0.1", 8090);
            os = socket.getOutputStream();
            dos = new DataOutputStream(os);
            Long pastTime, surplusTime, interval;
            if (socket != null && os != null) {
                while (threadState.equals(SensorState.using)) {
                    if (sensor.getState() == null || sensor.getState().equals(SensorState.offline) || sensor.getLatestMsg() == null) {
                        Thread.sleep(standardSleepTime);
                        continue;
                    }
                    String msg = sensor.getId().toString() + "/" + sensor.getLatestMsg();
                    System.out.println(msg);
                    dos.writeUTF(msg + "\r\n");
                    dos.flush();
                    pastTime = Long.valueOf(0);
                    surplusTime = interval = sensor.getInterval();
                    //System.out.println(sensor.getInterval() + " " + sensor.getState().name());
                    if (standardSleepTime >= interval) {
                        Thread.sleep(interval);
                    } else {
                        while (surplusTime > 0) {
                            Thread.sleep(standardSleepTime > surplusTime ? surplusTime : standardSleepTime);
                            surplusTime -= standardSleepTime;
                            pastTime += standardSleepTime;
                            if (pastTime > sensor.getInterval() ||
                                    sensor.getState().equals(SensorState.offline)) break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        this.threadState = SensorState.destroy;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }


    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
