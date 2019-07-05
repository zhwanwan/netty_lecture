package com.lec.netty.handler3;

/**
 * Netty--TCP粘包拆包
 */
public class PersonProtocol {

    private int length;
    private byte[] content;

    public PersonProtocol(int length, byte[] content) {
        this.length = length;
        this.content = content;
    }

    public PersonProtocol() {
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

}
