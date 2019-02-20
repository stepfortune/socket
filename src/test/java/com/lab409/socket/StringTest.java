package com.lab409.socket;

import com.lab409.socket.demoServer.enums.SensorType;

import java.sql.Date;
import java.text.SimpleDateFormat;


public class StringTest {
    public static void main(String[] args) {
        String str =  "asdfa";
        try {
            Long l = Long.valueOf(str);
            System.out.println(l);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        /*SensorType type = SensorType.valueOf("thunder");
        System.out.println(type);
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(date));
*/
        
        //System.out.println();
    }
}
