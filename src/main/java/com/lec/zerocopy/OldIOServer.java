package com.lec.zerocopy;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 传统的服务器端
 *
 * @author zhwanwan
 * @create 2019-06-28 4:43 PM
 */
public class OldIOServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8899);
        while (true) {
            Socket socket = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            try {
                byte[] byteArray = new byte[4096];

                //获取对端的数据
                while (true) {
                    int readCount = dataInputStream.read(byteArray, 0, byteArray.length);
                    if( -1 == readCount) break; //-1表示到达文件尾,退出循环
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

}
