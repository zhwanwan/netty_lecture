package com.lec.netty.example3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author zhwanwan
 * @create 2019-06-12 7:45 AM
 */
public class MyChatClientHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 客户端的输入来自于控制台
     * 此处直接在控制台打印服务端返回的数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);
    }
}
