package com.lec.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Selector
 *
 * @author zhwanwan
 * @create 2019-06-27 4:05 PM
 */
public class NioTest12 {

    public static void main(String[] args) throws IOException {
        int[] ports = new int[5];

        ports[0] = 5000;
        ports[1] = 5001;
        ports[2] = 5002;
        ports[3] = 5003;
        ports[4] = 5004;

        //构造Selector
        Selector selector = Selector.open();
        //System.out.println(SelectorProvider.provider().getClass()); //sun.nio.ch.WindowsSelectorProvider
        for (int i = 0, len = ports.length; i < len; i++) {
            //获取Channel
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            //设置Channel为非阻塞
            serverSocketChannel.configureBlocking(false);
            //获取Socket
            ServerSocket serverSocket = serverSocketChannel.socket();
            //定义address
            InetSocketAddress address = new InetSocketAddress(ports[i]);
            //绑定socket到指定address
            serverSocket.bind(address);

            //将当前Channel注册到Selector
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("监听端口: " + ports[i]);
        }

        while (true) {

            int numbers = selector.select();
            System.out.println("numbers: " + numbers);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //System.out.println("selectedKeys: " + selectionKeys);
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            ByteBuffer byteBuffer = ByteBuffer.allocate(512);
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();

                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);

                    socketChannel.register(selector, SelectionKey.OP_READ);
                    iterator.remove();
                    System.out.println("获得客户端连接: " + socketChannel);

                } else if (selectionKey.isReadable()) {
                    //获取客户端SC
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    int bytesRead = 0;
                    while (true) {
                        byteBuffer.clear();
                        int read = socketChannel.read(byteBuffer);
                        if (read <= 0) break;
                        byteBuffer.flip();
                        socketChannel.write(byteBuffer);
                        bytesRead += read;
                    }
                    System.out.println("读取: " + bytesRead + ",来自于: " + socketChannel);
                    iterator.remove();
                }
            }
        }
    }


}
