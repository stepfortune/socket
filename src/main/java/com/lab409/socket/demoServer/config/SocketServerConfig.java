package com.lab409.socket.demoServer.config;

import com.lab409.socket.demoServer.model.Sensor;
import com.lab409.socket.demoServer.netty.handlerInitializer.SocketServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.net.InetSocketAddress;
import java.util.*;

@Configuration
@ComponentScan("com.lab409.socket.demoServer.netty")
public class SocketServerConfig {
    @Value("${socket.server.worker.thread.count}")
    private Integer workerCount;

    @Value("${socket.server.boss.thread.count}")
    private Integer bossCount;

    @Value("${socket.server.so_keepalive}")
    private boolean keepAlive;

    @Value("${socket.server.port}")
    private Integer port;

    @Value("${socket.server.backlog}")
    private Integer backlog;

    @Value("${socket.client.num}")
    private Integer client_num;

    @Bean(name = "clientNum")
    public Integer getClientNum() {
        return client_num;
    }

    @SuppressWarnings("unchecked")
    @Bean(name = "serverBootstrap")
    public ServerBootstrap serverBootstrap() {
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup(), workerGroup())
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .childHandler(channelInitializer);
        Map<ChannelOption<?>, Object> tcpChannelOptions = tcpChannelOptions();
        Set<ChannelOption<?>> keySet = tcpChannelOptions.keySet();
        for (@SuppressWarnings("rawtypes") ChannelOption option : keySet) {
            b.option(option, tcpChannelOptions.get(option));
        }
        return b;
    }

    @Autowired
    @Qualifier("socketServerInitializer")
    private SocketServerInitializer channelInitializer;

    @Bean(name = "tcpChannelOptions")
    public Map<ChannelOption<?>, Object> tcpChannelOptions() {
        Map<ChannelOption<?>, Object> options = new HashMap<>();
        options.put(ChannelOption.SO_KEEPALIVE, keepAlive);
        options.put(ChannelOption.SO_BACKLOG, backlog);
        return options;
    }

    @Bean(name = "bossGroup", destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup bossGroup() {
        return new NioEventLoopGroup(bossCount);
    }

    @Bean(name = "workerGroup", destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup workerGroup() {
        return new NioEventLoopGroup(workerCount);
    }

    @Bean(name = "socketServerAddress")
    public InetSocketAddress tcpPort() {
        return new InetSocketAddress(port);
    }

    @Bean(name = "clientList_1")
    @Scope("singleton")
    public List<Sensor> clientList1() {
        return new ArrayList<Sensor>(50);
    }

    @Bean(name= "clientList_2")
    @Scope("singleton")
    public List<Sensor> clientList2() {
        return new ArrayList<Sensor>(50);
    }
}
