package com.lec.zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author zhwanwan
 * @create 2019-06-28 5:09 PM
 */
public class NewIOServer {

    public static void main(String[] args) throws IOException {

        InetSocketAddress address = new InetSocketAddress(8899);

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.setReuseAddress(true);
        serverSocket.bind(address);

        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(true);//此处没有Selector,一定是阻塞的,可以不写

            int readCount = 0;
            while (-1 != readCount) {
                try {
                    readCount = socketChannel.read(byteBuffer); //将数据读入服务端Channel
                } catch (Exception e) {
                    e.printStackTrace();
                }
                /**
                 * flip,clear及rewind区别 *
                 * Clear: 把position设为0，把limit设为capacity，一般在把数据写入Buffer前调用。
                 * Flip: 把limit设为当前position，把position设为0，一般在从Buffer读出数据前调用。
                 * Rewind: 把position设为0，limit不变，一般在把数据重写入Buffer前调用。
                 */
                byteBuffer.rewind();
                //byteBuffer.clear(); //可以用在read前

            }

        }




    }

}
