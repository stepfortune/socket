package com.lab409.socket.old.socket.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;



class ServerReceiveRunnable implements Runnable{   // receive msg from client

    private Socket s=null;
    public ServerReceiveRunnable(Socket s, ArrayList<Socket> arrayList){
        this.s=s;
    }

    public void run() {
        // TODO Auto-generated method stub
        InputStream is=null;
        OutputStream os = null;
        DataInputStream dis=null;
        DataOutputStream dos = null;
        String orderFromClient = null;
        String result =  "asdfasdf"+"\r\n";
        try {
            os = s.getOutputStream();
            dos = new DataOutputStream(os);
            while(true){
                is=s.getInputStream();
                dis=new DataInputStream(is);
                orderFromClient = dis.readUTF();
                System.out.println("server received from " + s.getRemoteSocketAddress() + " : "+ orderFromClient);
                //result = orderFromClient;
                dos.writeUTF(orderFromClient+"\r\n");

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

class ServerSendRunnable implements Runnable{  // send msg to all clients connected to server
                                               // use the socket array
    private ArrayList<Socket> socketArrayList;
    public ServerSendRunnable(ArrayList<Socket> arrayList) {
        this.socketArrayList = arrayList;
    }

    public void run() {
        // TODO Auto-generated method stub
        OutputStream os=null;
        DataOutputStream dos=null;
        try {
            while (true) {
                Scanner in = new Scanner(System.in);
                String line = in.nextLine();
                for (Socket s : socketArrayList) {
                    os = s.getOutputStream();
                    dos = new DataOutputStream(os);
                    dos.writeUTF(line);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}

public class TestThreadServer {    //server main function location
                                   // open one thread to watch the listener of keyboard
                                   // open n thread to keep the connections with the clients(n is the number of client)
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        ServerSocket ss=null;
        ss=new ServerSocket(8080);
        ArrayList<Socket> arrayList = new ArrayList<>();
        boolean has_samle_socket = false;

        Runnable r2=new ServerSendRunnable(arrayList);
        Thread sendmsg=new Thread(r2);
        sendmsg.start();

        while(true){
            Socket s=ss.accept();
            if (arrayList.size() != 0) {
                for (Socket stmp : arrayList) {
                    if (stmp.getRemoteSocketAddress().toString().equals(s.getRemoteSocketAddress().toString())) {
                        has_samle_socket = true;
                        break;
                    }
                }
                if (!has_samle_socket) {
                    arrayList.add(s);

                    Runnable r1=new ServerReceiveRunnable(s, null);
                    Thread t1=new Thread(r1);
                    t1.start();

                    has_samle_socket = false;
                }
            }
            else{
                arrayList.add(s);
                Runnable r1=new ServerReceiveRunnable(s, null);
                Thread t1=new Thread(r1);
                t1.start();
            }
        }
    }
}