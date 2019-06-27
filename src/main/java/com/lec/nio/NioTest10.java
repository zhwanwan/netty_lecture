package com.lec.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/** 文件锁-File Lock:了解
 * 共享锁
 * 排他锁
 * @author zhwanwan
 * @create 2019-06-27 1:26 PM
 */
public class NioTest10 {

    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest9.txt","rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        FileLock fileLock = fileChannel.lock(3,6,true);
        System.out.println("Valid: " + fileLock.isValid());
        System.out.println("Lock Type: " + fileLock.isShared());

        fileLock.release(); //释放锁
        randomAccessFile.close();
    }
}
