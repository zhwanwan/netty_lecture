package com.lec.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * 一个Channel
 *
 * @author zhwanwan
 * @create 2019-06-27 9:32 PM
 */
public class NioServer {

    private static Map<String, SocketChannel> clientMap = new HashMap<>();

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8899));

        Selector selector = Selector.open();
        //将channel注册到selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        Charset charset = Charset.forName("UTF-8");
        //开始服务器监听
        while (true) {
            try {
                selector.select();

                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                selectionKeys.forEach(key -> {
                    final SocketChannel client;
                    try {
                        if (key.isAcceptable()) {
                            //客户端发起连接
                            ServerSocketChannel server = (ServerSocketChannel) key.channel();
                            client = server.accept();
                            client.configureBlocking(false);
                            client.register(selector, SelectionKey.OP_READ);

                            String mapKey = "[" + UUID.randomUUID() + "]";
                            clientMap.put(mapKey, client);

                        } else if (key.isReadable()) {
                            client = (SocketChannel) key.channel();
                            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                            int count = client.read(readBuffer);
                            if (count > 0) {
                                readBuffer.flip(); //反转
                                //获取数据
                                String receivedMessage = String.valueOf(charset.decode(readBuffer).array());
                                System.out.println(client + ": " + receivedMessage);
                            }

                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                });
                selectionKeys.clear();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

}
