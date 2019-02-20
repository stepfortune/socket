package com.lab409.socket.demoServer.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;


@Component
@Qualifier("socketServer")
public class SocketServer {

    @Autowired
    @Qualifier("serverBootstrap")
    private ServerBootstrap bootstrap;

    @Autowired
    @Qualifier("socketServerAddress")
    private InetSocketAddress port;

    private Channel serverChannel;

    public void start() throws Exception {
        serverChannel = bootstrap.bind(port).sync().channel().closeFuture().sync().channel();
    }

    @PreDestroy
    public void stop() {
        serverChannel.close();
        serverChannel.parent().close();
    }

}
