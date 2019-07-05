package com.lec.netty.handler3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**根据自定义协议进行解码
 * @author zhwanwan
 * @create 2019-07-06 1:33 AM
 */
public class MyPersonDecoder extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        System.out.println("MyPersonDecoder decode invoked");
        int length = in.readInt(); //读取长度信息
        byte[] content = new byte[length]; //构造一个相同长度的字节数组
        in.readBytes(content); //将字节数组读入缓冲

        //构造自定义协议并放入channel
        PersonProtocol personProtocol = new PersonProtocol();
        personProtocol.setLength(length);
        personProtocol.setContent(content);

        out.add(personProtocol);

    }
}
