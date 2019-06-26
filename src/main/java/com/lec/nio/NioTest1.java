package com.lec.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * @author zhwanwan
 * @create 2019-06-26 1:48 PM
 */
public class NioTest1 {
    public static void main(String[] args) {

        IntBuffer buffer = IntBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); i++) {
            int randomNumber = new SecureRandom().nextInt(20);
            buffer.put(randomNumber);
        }
        /**
         * makes a buffer ready for a new sequence of channel-write or relative get operations:
         * It sets the limit to the current position and then sets the position to zero.
         */
        buffer.flip(); //读写切换
        System.out.println(buffer.isReadOnly());//false
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }
}
