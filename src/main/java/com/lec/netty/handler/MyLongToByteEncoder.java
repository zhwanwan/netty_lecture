package com.lec.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/** 编码器: 本质上是一种出站处理器,因此,编码一定是一种ChannelOutboundHandler.
 * 将Long转换成Byte
 * @author zhwanwan
 * @create 2019-07-05 1:45 PM
 */
public class MyLongToByteEncoder extends MessageToByteEncoder<Long> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {
        System.out.println("encode invoked!");
        System.out.println(msg);
        out.writeLong(msg);
    }
}
