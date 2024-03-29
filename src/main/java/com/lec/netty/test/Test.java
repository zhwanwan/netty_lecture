package com.lec.netty.test;

import io.netty.util.NettyRuntime;
import io.netty.util.internal.SystemPropertyUtil;

/**
 * @author zhwanwan
 * @create 2019-06-29 12:06 PM
 */
public class Test {
    public static void main(String[] args) {
        int result = Math.max(1, SystemPropertyUtil.getInt(
                "io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
        System.out.println(result);
    }
}
