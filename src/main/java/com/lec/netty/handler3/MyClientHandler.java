package com.lec.netty.handler3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author zhwanwan
 * @create 2019-07-06 1:48 AM
 */
public class MyClientHandler extends SimpleChannelInboundHandler<PersonProtocol> {

    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        for (int i = 0; i < 10; i++) {

            String message = "sent from client";
            byte[] content = message.getBytes(CharsetUtil.UTF_8);
            int length = content.length;

            PersonProtocol personProtocol = new PersonProtocol(length, content);

            ctx.writeAndFlush(personProtocol);

        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {

        int length = msg.getLength();
        byte[] content = msg.getContent();

        System.out.println("客户端接收的消息: ");
        System.out.println("长度: " + length);
        System.out.println("内容: " + new String(content, CharsetUtil.UTF_8));

        System.out.println("客户端接收到的消息数量: " + (++this.count));

    }

}
