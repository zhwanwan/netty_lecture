package com.lec.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 *
 * ByteBuf
 *      +-------------------+------------------+------------------+
 *      | discardable bytes |  readable bytes  |  writable bytes  |
 *      |                   |     (CONTENT)    |                  |
 *      +-------------------+------------------+------------------+
 *      |                   |                  |                  |
 *      0      <=      readerIndex   <=   writerIndex    <=    capacity
 * @author zhwanwan
 * @create 2019-07-04 4:19 PM
 */
public class ByteBufTest0 {

    public static void main(String[] args) {

        ByteBuf buf = Unpooled.buffer(10);
        for (int i = 0; i < 10; i++) {
            buf.writeByte(i);
        }
        /*for (int i = 0, capacity = buf.capacity(); i < capacity; i++) {
            System.out.println(buf.getByte(i));
        }*/

        for (int i = 0, capacity = buf.capacity(); i < capacity; i++) {
            System.out.println(buf.readByte());
        }
    }

}
