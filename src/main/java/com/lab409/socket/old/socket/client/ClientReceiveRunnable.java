package com.lab409.socket.old.socket.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

public class ClientReceiveRunnable implements Runnable{
    private final Logger logger = LoggerFactory.getLogger(ClientReceiveRunnable.class);

    private Socket s=null;

    public ClientReceiveRunnable(Socket s){
        this.s=s;
    }

    public void run() {
        // TODO Auto-generated method stub
        InputStream is=null;
        DataInputStream dis=null;
        BufferedReader reader;
        try {
            while(true){
                is=s.getInputStream();
                reader =new BufferedReader(new InputStreamReader(is,"UTF-8"));
                logger.info(s.getRemoteSocketAddress()+ " " +reader.readLine());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
