package com.lec.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author zhwanwan
 * @create 2019-06-27 7:47 AM
 */
public class NioTest8 {

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("input2.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("output2.txt");
        FileChannel inputChannel = fileInputStream.getChannel();
        FileChannel outputChannel = fileOutputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocateDirect(512);

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
