package com.lec.netty.handler3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.UUID;

/**
 * @author zhwanwan
 * @create 2019-07-06 1:40 AM
 */
public class MyServerHandler extends SimpleChannelInboundHandler<PersonProtocol> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
        //1.服务端接收客户端消息
        int length = msg.getLength();
        byte[] content = msg.getContent();
        System.out.println("服务端接收到的数据:");
        System.out.println("长度: " + length);
        System.out.println("内容: " + new String(content, CharsetUtil.UTF_8));
        System.out.println("服务端接收到的消息数量: " + (++this.count));

        //2.服务端向客户端回写消息
        String responseMessage = UUID.randomUUID().toString();
        byte[] responseContent = responseMessage.getBytes(CharsetUtil.UTF_8);
        int responseLength = responseContent.length;
        PersonProtocol personProtocol = new PersonProtocol(responseLength, responseContent);
        ctx.writeAndFlush(personProtocol);



    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
