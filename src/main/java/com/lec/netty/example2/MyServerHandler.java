package com.lec.netty.example2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @author zhwanwan
 * @create 2019-06-06 6:16 AM
 */
public class MyServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 问题0702:
     * channelRead0是由谁来调用的呢?
     * workerGroup(sub-reactor--IO线程池中某个线程)调用
     * --如果这个方法执行比较耗时,IO线程会阻塞,那么就应该新开业务线程池来异步处理
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " " + msg); //read读取客户端信息
        ctx.channel().writeAndFlush("from server: " + UUID.randomUUID()); //向通道输出服务端消息
    }

    /**
     * close cxt if exception thrown
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
