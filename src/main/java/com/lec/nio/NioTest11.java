package com.lec.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * 关于Buffer的Scattering与Gathering
 * windows可以使用telnet:
 * >>telnet localhost 8899
 *
 * @author zhwanwan
 * @create 2019-06-27 1:30 PM
 */
public class NioTest11 {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(8899);
        serverSocketChannel.socket().bind(address);

        int messageLength = 2 + 3 + 4;

        ByteBuffer[] buffers = new ByteBuffer[3];
        buffers[0] = ByteBuffer.allocate(2);
        buffers[1] = ByteBuffer.allocate(3);
        buffers[2] = ByteBuffer.allocate(4);

        //Accepts a connection made to this channel's socket.
        SocketChannel socketChannel = serverSocketChannel.accept();

        while (true) {
            //读入服务端
            int bytesRead = 0;
            while (bytesRead < messageLength) {
                long r = socketChannel.read(buffers);
                bytesRead += r;
                System.out.println("bytesRead: " + bytesRead);
                //JDK8-方法引用
                Arrays.stream(buffers)
                        .map(buffer -> "position:" + buffer.position() + ", limit: " + buffer.limit())
                        .forEach(System.out::println);
            }
            //flip buffer用于写入
            Arrays.stream(buffers).forEach(Buffer::flip);

            //写入客户端
            long bytesWritten = 0;
            while (bytesWritten < messageLength) {
                long w = socketChannel.write(buffers);
                bytesWritten += w;
            }
            Arrays.asList(buffers).forEach(Buffer::clear);

            System.out.println("bytesRead: " + bytesRead + ", bytesWritten: " + bytesWritten + ", messageLength: " + messageLength);
        }
    }

}
