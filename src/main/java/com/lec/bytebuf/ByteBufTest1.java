package com.lec.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * @author zhwanwan
 * @create 2019-07-04 6:44 PM
 */
public class ByteBufTest1 {

    public static void main(String[] args) {

        ByteBuf byteBuf = Unpooled.copiedBuffer("张hello world", Charset.forName("utf-8"));
        System.out.println("original bytes length: " + "hello world".getBytes().length);

        //byteBuf.hasArray() true->heap buffer
        if (byteBuf.hasArray()) {
            System.out.println(byteBuf);
            byte[] content = byteBuf.array();
            System.out.println("length of content: " + content.length);
            System.out.println(new String(Arrays.copyOf(content,byteBuf.readableBytes()), Charset.forName("utf-8")));
            System.out.println(byteBuf.arrayOffset());
            System.out.println(byteBuf.readerIndex());
            System.out.println(byteBuf.writerIndex());
            System.out.println(byteBuf.capacity());

            int length = byteBuf.readableBytes();
            System.out.println(length);

            for (int i = 0,len = byteBuf.readableBytes();i<len;i++) {
                System.out.print((char) byteBuf.getByte(i));
            }
            System.out.println();
            System.out.println(byteBuf.getCharSequence(0,4,Charset.forName("utf-8")));
            System.out.println(byteBuf.getCharSequence(4,6,Charset.forName("utf-8")));

        }

    }
}
