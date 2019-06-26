package com.lec.nio;

import java.nio.ByteBuffer;

/**
 * slice buffer--原有buffer的快照,与原有buffer共享底层数组
 * 将原有buffer[2~6)元素乘以2
 *
 * @author zhwanwan
 * @create 2019-06-26 10:20 PM
 */
public class NioTest6 {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0, len = buffer.capacity(); i < len; i++) {
            buffer.put((byte) i);
        }
        buffer.position(2).limit(6);
        ByteBuffer sliceBuffer = buffer.slice();
        for (int i = 0, len = sliceBuffer.capacity(); i < len; i++) {
            byte b = sliceBuffer.get(i);
            b *= 2;
            sliceBuffer.put(i, b);
        }
        buffer.position(0).limit(buffer.capacity()); //恢复
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }
}
