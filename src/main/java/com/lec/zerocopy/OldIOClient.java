package com.lec.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author zhwanwan
 * @create 2019-06-28 4:52 PM
 */
public class OldIOClient {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 8899);
        String fileName = "C:\\Users\\zhwanwan\\Desktop\\spark-2.4.3-bin-hadoop2.7.tgz";

        InputStream inputStream = new FileInputStream(fileName); //输入流,相对于文件来说
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream()); //用于网络传输,客户端发送到服务端

        byte[] buffer = new byte[4096];
        long readCount = 0;
        long total = 0;

        long startTime = System.currentTimeMillis();

        while ((readCount = inputStream.read(buffer, 0, buffer.length)) >= 0) {
            total += readCount;
            dataOutputStream.write(buffer);
        }

        System.out.println("发送总字节数: " + total + ", 耗时: " + (System.currentTimeMillis() - startTime));
        dataOutputStream.close();
        socket.close();
        inputStream.close();


    }

}
