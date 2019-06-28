package com.lec.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/** 刚开始遇到问题:https://blog.csdn.net/forget_me_not1991/article/details/80722386
 * 原来大文件传输的时候，windows对一次传输的大小是有限制的；
 * 解决方案:通过transferTo返回的实际传输大小进行多次传输即可;
 * 总结:
 * 不管是windows系统还是linux系统，使用fileChannel.transferTo传输文件，正确的做法就是要根据transferTo返回的实际传输字节数进行多次传输；
 *
 * 通过这种方式传输,效率远远高于传统网络传输
 * fileChannel.transferTo通过NIO的零拷贝来实现,效率大大提升
 *
 * @author zhwanwan
 * @create 2019-06-28 6:43 PM
 */
public class NewIOClient {


    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel = SocketChannel.open();
        //连接服务端
        socketChannel.connect(new InetSocketAddress("localhost", 8899));
        //设置阻塞模式
        socketChannel.configureBlocking(true);

        String fileName = "C:\\Users\\zhwanwan\\Desktop\\spark-2.4.3-bin-hadoop2.7.tgz";

        FileChannel fileChannel = new FileInputStream(fileName).getChannel();

        long startTime = System.currentTimeMillis();
        long size = fileChannel.size();
        long total = 0;
        long position = 0;
        while (position < size) {
            long transferCount = fileChannel.transferTo(position, fileChannel.size(), socketChannel);
            if (transferCount <= 0) break;
            total += transferCount;
            position += transferCount;

        }
        System.out.println("发送总字节数: " + total + ",耗时: " + (System.currentTimeMillis() - startTime));


        /**
         * 关闭客户端Channel,此操作一定要关闭,不然服务端还会从服务端读取数据,会引发异常
         * https://blog.csdn.net/woshixuye/article/details/53738033
         * 原因: 客户端强制关闭了连接（没有调用SocketChannel的close方法），服务端还在read事件中，此时读取客户端的信息时会报错。
         */
        socketChannel.close();


    }
}
