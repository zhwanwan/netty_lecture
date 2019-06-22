package com.lec.grpc;

import com.lec.proto.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Iterator;

/**
 * @author zhwanwan
 * @create 2019-06-22 2:37 AM
 */
public class GrpcClient {

    public static void main(String[] args) {

        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost", 8899).usePlaintext().build();
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub =
                StudentServiceGrpc.newBlockingStub(managedChannel);
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
        StreamObserver<StudentRepList> observer = new StreamObserver<StudentRepList>() {
            @Override
            public void onNext(StudentRepList value) {
                value.getStudentRepList().forEach(sp -> {
                    System.out.println("[" + sp.getName() + ", " + sp.getAge() + ", " + sp.getCity() + "]");
                    System.out.println("*********");
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

        StudentServiceGrpc.StudentServiceStub stub = StudentServiceGrpc.newStub(managedChannel);
        //只要客户端以stream方式向服务端发送请求,这种请求时异步的
        StreamObserver<StudentReq> studentReqStreamObserver = stub.getStudentsWrapperByAges(observer);
        studentReqStreamObserver.onNext(StudentReq.newBuilder().setAge(20).build());
        studentReqStreamObserver.onNext(StudentReq.newBuilder().setAge(30).build());
        studentReqStreamObserver.onNext(StudentReq.newBuilder().setAge(40).build());
        studentReqStreamObserver.onNext(StudentReq.newBuilder().setAge(50).build());

    }
}
