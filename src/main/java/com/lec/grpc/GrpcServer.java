package com.lec.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author zhwanwan
 * @create 2019-06-22 2:32 AM
 */
public class GrpcServer {
    private Server server;

    private void start() throws IOException {

        this.server = ServerBuilder.forPort(8899).addService(new StudentServiceImpl()).build().start();
        System.out.println("服务器启动...");

        /**
         * 回调钩子
         */
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("关闭JVM");
            GrpcServer.this.stop();
        }));
        System.out.println("执行到这里...");

    }

    private void stop() {
        if (null != this.server) {
            System.out.println("服务器关闭...");
            this.server.shutdown();
        }
    }

    private void awaitTermination() throws InterruptedException {
        if (null != this.server) {
//            this.server.awaitTermination(3000, TimeUnit.MILLISECONDS); //服务器3s后退出
            this.server.awaitTermination();
        }
    }

    public static void main(String[] args) throws Exception {
        GrpcServer server = new GrpcServer();
        server.start();
        server.awaitTermination();
    }
}
