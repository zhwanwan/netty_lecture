package com.lec.grpc;

import com.lec.proto.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

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



    }
}
