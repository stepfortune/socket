package com.lab409.socket.old.socket.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.util.ByteProcessor;
import io.netty.util.CharsetUtil;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

public class TestThreadClient {

    public static void main(String[] args) throws Exception{
        Socket s=null;

        s=new Socket("127.0.0.1",8090);
        Runnable r1=new ClientSendRunnable(s);
        Thread t1=new Thread(r1);
        t1.start();

        Runnable r2=new ClientReceiveRunnable(s);
        Thread t2=new Thread(r2);
        t2.start();
    }

}