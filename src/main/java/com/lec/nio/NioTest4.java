package com.lec.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Buffer: P(Position),L(Limit),C(Capacity)
 *
 * @author zhwanwan
 * @create 2019-06-26 8:47 PM
 */
public class NioTest4 {
    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream("input.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("output.txt");
        FileChannel inputChannel = fileInputStream.getChannel();
        FileChannel outputChannel = fileOutputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(512);

        while (true) {
            buffer.clear(); //must exist
            //缓冲区读入数据
            int read = inputChannel.read(buffer);
            System.out.println("read: " + read);
            if (read == -1) break;

            buffer.flip();
            //将缓冲区数据写出到文件
            outputChannel.write(buffer);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
