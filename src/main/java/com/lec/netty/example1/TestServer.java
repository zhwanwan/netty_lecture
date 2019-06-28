package com.lec.netty.example1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author zhwanwan
 * @create 2019-06-04 7:48 AM
 */
public class TestServer {

    public static void main(String[] args) {

        /**
         * 网络编程很多都是一个死循环,用于监听客户端
         * EventLoopGroup 是一个死循环,一直等待监听客户端连接
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();   //获取客户端连接
        EventLoopGroup workerGroup = new NioEventLoopGroup(); //处理客户端连接

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap(); //服务器启动类
            /**
             * 如果出现平台不支持的问题，可以将HelloServer 类代码中的
             * bootstrap.channel(NioSctpServerChannel.class);
             * 换成 NioServerSocketChannel.class 即可。
             *
             * handler->bossGroup
             * childHandler->workerGroup 请求处理器
             *
             */
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new TestServerInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync(); //关闭通道
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }
}
