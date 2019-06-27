package com.lec.nio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 从键盘输入获取数据
 *
 * @author zhwanwan
 */
public class NioClient {

    public static void main(String[] args) {

        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);

            Selector selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            socketChannel.connect(new InetSocketAddress(8899));
            ByteBuffer writerBuffer = ByteBuffer.allocate(1024);
            while (true) {
                selector.select();
                Set<SelectionKey> keySet = selector.selectedKeys();
                for (SelectionKey selectionKey : keySet) {
                    if (selectionKey.isConnectable()) {
                        SocketChannel client = (SocketChannel) selectionKey.channel();
                        if (client.isConnectionPending()) {
                            client.finishConnect();
                            writerBuffer.clear();
                            writerBuffer.put((LocalDateTime.now() + " 连接成功").getBytes());
                            writerBuffer.flip();
                            client.write(writerBuffer);

                            //新的线程进行客户端(控制台)输入数据
                            ExecutorService executorService = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
                            executorService.submit(() -> {
                                while (true) {
                                    try {
                                        writerBuffer.clear();
                                        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                                        String sendMessage = br.readLine();
                                        writerBuffer.put(sendMessage.getBytes());
                                        writerBuffer.flip();
                                        client.write(writerBuffer);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                        //注册读取事件
                        client.register(selector, SelectionKey.OP_READ);
                    } else if (selectionKey.isReadable()) {
                        SocketChannel client = (SocketChannel) selectionKey.channel();
                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                        int count = client.read(readBuffer);
                        if (count > 0) {
                            String receiveMessage = new String(readBuffer.array(), 0, count);
                            System.out.println(receiveMessage);
                        }
                    }
                }
                keySet.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
