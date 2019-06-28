package com.lec.netty.example5;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/** 一个基于长连接完整的例子--WebSocketChannelInitializer
 * 通过浏览器进行测试,使用ws协议,首先向服务发起http连接然后升级成websocket,接着通过JS相关的API进行处理
 * 页面参见webapp/test.html
 * 启动方法:
 * 1.Run MyServer.main
 * 2.Run test.html
 * @author zhwanwan
 * @create 2019-06-12 9:31 AM
 */
public class MyServer {

    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))          // handler -> bossGroup
                    .childHandler(new WebSocketChannelInitializer());   // childHandler -> workerGroup

            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
