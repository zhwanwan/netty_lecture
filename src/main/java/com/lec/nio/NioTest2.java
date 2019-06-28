package com.lec.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author zhwanwan
 * @create 2019-06-26 2:58 PM
 */
public class NioTest2 {
    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream("NioTest2.txt");
        FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        fileChannel.read(byteBuffer);
        byteBuffer.flip();//读写切换--操作翻转
        while (byteBuffer.hasRemaining()) {
            byte b = byteBuffer.get();
            System.out.print((char) b);
        }
        fileInputStream.close();
    }
}
