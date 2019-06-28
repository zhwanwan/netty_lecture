package com.lec.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/** 字节编码
 * @author zhwanwan
 * @create 2019-06-28 5:58 AM
 */
public class NioTest13 {

    public static void main(String[] args) throws Exception {
        String inputFile = "NioTest13_In.txt";
        String outputFile = "NioTest13_Out.txt";

        RandomAccessFile inputRandomAccessFile = new RandomAccessFile(inputFile, "r");
        RandomAccessFile outputRandomAccessFile = new RandomAccessFile(outputFile, "rw");

        long inputLength = new File(inputFile).length();

        FileChannel inputFileChannel = inputRandomAccessFile.getChannel();
        FileChannel outputFileChannel = outputRandomAccessFile.getChannel();

        //内存映射文件
        MappedByteBuffer inputData = inputFileChannel.map(FileChannel.MapMode.READ_ONLY, 0, inputLength);

        /*System.out.println("+++++++++++++++");
        Charset.availableCharsets().forEach((k, v) -> {
            System.out.println(k + ", " + v);
        });
        System.out.println("+++++++++++++++");*/

        Charset charset = Charset.forName("UTF-8");
        CharsetDecoder decoder = charset.newDecoder();//解码:字节数据->字符串
        CharsetEncoder encoder = charset.newEncoder();//编码:字符串->字节数组

        CharBuffer charBuffer = decoder.decode(inputData);
        ByteBuffer outputData = encoder.encode(charBuffer);

        outputFileChannel.write(outputData);

        inputRandomAccessFile.close();
        outputRandomAccessFile.close();

    }

}
