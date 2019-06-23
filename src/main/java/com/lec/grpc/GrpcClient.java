package com.lec.grpc;

import com.lec.proto.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.time.LocalDateTime;
import java.util.Iterator;

/**
 * @author zhwanwan
 * @create 2019-06-22 2:37 AM
 */
public class GrpcClient {

    public static void main(String[] args) {

        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost", 8899).usePlaintext().build();
        //blocking-style stub
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.newBlockingStub(managedChannel);

        //async (异步)stub
        StudentServiceGrpc.StudentServiceStub stub = StudentServiceGrpc.newStub(managedChannel);

        MyResponse myResponse = blockingStub.getRealNameByUsername(
                MyRequest.newBuilder().setUsername("张三").build()
        );
        System.out.println(myResponse.getRealname());
        System.out.println("---------------------------------------------");

        Iterator<StudentRep> iterator = blockingStub.getStudentByAge(StudentReq.newBuilder().setAge(20).build());

        iterator.forEachRemaining(sp -> {
            System.out.println("[" + sp.getName() + ", " + sp.getAge() + ", " + sp.getCity() + "]");
        });
        System.out.println("---------------------------------------------");

        /**
         * 客户端回调-->服务端响应对象
         */
        /*StreamObserver<StudentRepList> observer = new StreamObserver<StudentRepList>() {
            @Override
            public void onNext(StudentRepList value) {
                value.getStudentRepList().forEach(sp -> {
                    System.out.println("[" + sp.getName() + ", " + sp.getAge() + ", " + sp.getCity() + "]");
                    System.out.println("**********************");
                });
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Completed!");
            }
        };


        //只要客户端以stream方式向服务端发送请求,这种请求时异步的
        StreamObserver<StudentReq> studentReqStreamObserver = stub.getStudentsWrapperByAges(observer);
        studentReqStreamObserver.onNext(StudentReq.newBuilder().setAge(20).build());
        studentReqStreamObserver.onNext(StudentReq.newBuilder().setAge(30).build());
        studentReqStreamObserver.onNext(StudentReq.newBuilder().setAge(40).build());
        studentReqStreamObserver.onNext(StudentReq.newBuilder().setAge(50).build());

        studentReqStreamObserver.onCompleted();*/


        /**
         * gRPC第四种以stream方式进行双向连接:服务器和客户端都以stream方式响应
         */
        StreamObserver<StreamRequest> requestStreamObserver = stub.biTalk(new StreamObserver<StreamResponse>() {
            @Override
            public void onNext(StreamResponse value) {
                //收到服务器返回的结果
                System.out.println(value.getResponseInfo());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted!");
            }
        });

        for(int i = 0;i<10;i++) {
            requestStreamObserver.onNext(StreamRequest.newBuilder().setRequestInfo(LocalDateTime.now().toString()).build());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
