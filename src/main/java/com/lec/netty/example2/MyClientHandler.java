package com.lec.netty.example2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/** sub-reactor回调
 * @author zhwanwan
 * @create 2019-06-06 6:36 AM
 */
public class MyClientHandler extends SimpleChannelInboundHandler {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println(ctx.channel().remoteAddress());
        System.out.println("client output:" + msg); //读取服务端信息
        ctx.writeAndFlush("from client: " + LocalDateTime.now());

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("来自客户端的问候!");
    }
}
