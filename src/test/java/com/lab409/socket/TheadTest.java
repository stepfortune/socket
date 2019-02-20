package com.lab409.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TheadTest {
    public static void main(String[] args) throws  Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str;
        MyThead1 thead1 = new MyThead1();
        thead1.msg = "msg1";
        Thread thread = new Thread(thead1);
        thread.start();
        while(true) {
            str = reader.readLine();
            if(str == null) continue;
            thead1.setMsg(str);
        }

    }
}

class MyThead1 implements Runnable {
    public String msg;
    public boolean over = false;
    public void setMsg(String msg) {
        this.msg = msg;
    }
    @Override
    public void run() {
        try {
            while(!over) {
                System.out.println("Mythread1: " + msg);
                Thread.sleep(2000);
            }
        }catch (Exception e) {
            System.out.println(e.toString());
        }

    }
}