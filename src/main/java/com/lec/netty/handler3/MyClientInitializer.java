package com.lec.netty.handler3;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author zhwanwan
 * @create 2019-07-06 1:52 AM
 */
public class MyClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new MyPersonEncoder());
        pipeline.addLast(new MyPersonDecoder());

        pipeline.addLast(new MyClientHandler());
    }
}
