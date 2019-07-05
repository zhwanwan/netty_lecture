package com.lec.netty.handler3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/** 根据自定义协议进行编码
 * @author zhwanwan
 * @create 2019-07-06 1:37 AM
 */
public class MyPersonEncoder extends MessageToByteEncoder<PersonProtocol> {

    @Override
    protected void encode(ChannelHandlerContext ctx, PersonProtocol msg, ByteBuf out) throws Exception {
        System.out.println("MyPersonEncoder encode invoked");
        out.writeInt(msg.getLength());    //长度:消息头
        out.writeBytes(msg.getContent()); //内容:消息体
    }
}
