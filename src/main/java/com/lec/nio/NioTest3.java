package com.lec.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author zhwanwan
 * @create 2019-06-26 3:10 PM
 */
public class NioTest3 {
    public static void main(String[] args) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream("NioTest3.txt");
        FileChannel fileChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        byte[] message = "hello world welcome,China".getBytes();
        for (int i = 0, len = message.length; i < len; i++) {
            byteBuffer.put(message[i]);
        }
        byteBuffer.flip();
        fileChannel.write(byteBuffer);
        fileOutputStream.close();
    }
}
