package com.lab409.socket.demoServer.netty.handler;

import com.lab409.socket.demoServer.model.Sensor;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Scope("singleton")
@Qualifier("socketServerHandler")
@ChannelHandler.Sharable
public class SocketServerHandler extends ChannelInboundHandlerAdapter {
    private final Logger logger = LoggerFactory.getLogger(SocketServerHandler.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    private Integer receive_num = 0;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String information = (String)msg;
        rabbitTemplate.convertAndSend("exchange","topic.msg", msg);
        this.receive_num += 1;
        logger.info(information + " " + receive_num);
        ctx.writeAndFlush("received\n\r");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

}
