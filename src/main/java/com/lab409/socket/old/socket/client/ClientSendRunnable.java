package com.lab409.socket.old.socket.client;

import java.io.*;
import java.net.Socket;

public class ClientSendRunnable implements Runnable{

    private Socket s = null;

    public ClientSendRunnable(Socket s){
        this.s=s;
    }

    public void run() {
        // TODO Auto-generated method stub
        OutputStream os=null;
        DataOutputStream dos=null;
        BufferedOutputStream bos;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder(100);
        try {
            while(true){
                /*String line = reader.readLine();
                if(line==null) continue;*/
                String line = "1/tomorrow has thunder";
                os=s.getOutputStream();
                dos = new DataOutputStream(os);

                dos.writeUTF(line+"\r\n");
                dos.flush();
                try {
                    Thread.sleep(2000);
                } catch (Exception e){

                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
