package com.lec.nio;

import java.nio.ByteBuffer;

/**
 * 只读Buffer
 *
 * @author zhwanwan
 * @create 2019-06-26 10:41 PM
 */
public class NioTest7 {

    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println(buffer.getClass());
        for (int i = 0, len = buffer.capacity(); i < len; i++) {
            buffer.put((byte) i);
        }
        ByteBuffer readonlyBuffer = buffer.asReadOnlyBuffer();
        System.out.println(readonlyBuffer.getClass());




    }

}
