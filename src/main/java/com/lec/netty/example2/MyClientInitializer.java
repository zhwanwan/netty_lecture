package com.lec.netty.example2;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/** ChannelPipeline --> 类似于一系列handler集合
 * @author zhwanwan
 * @create 2019-06-06 6:35 AM
 */
public class MyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new LengthFieldBasedFrameDecoder(
                Integer.MAX_VALUE, 0, 4, 0, 4)); //inbound
        pipeline.addLast(new LengthFieldPrepender(4)); //inboud
        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8)); //decoder, inbound
        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8)); //encoder, outbound
        pipeline.addLast(new MyClientHandler()); //customized handler, inbound
    }
}
