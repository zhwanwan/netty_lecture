package com.lec.netty.example2;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @author zhwanwan
 * @create 2019-06-06 6:09 AM
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new LengthFieldBasedFrameDecoder(
                Integer.MAX_VALUE, 0, 4, 0, 4)); //inbound
        pipeline.addLast(new LengthFieldPrepender(4)); //inbound
        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8)); //inbound
        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8)); //outbound
        pipeline.addLast(new MyServerHandler()); //customized handler, inbound

    }
}
