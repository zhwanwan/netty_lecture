package com.lec.grpc;

import com.lec.proto.*;
import io.grpc.stub.StreamObserver;

/**
 * @author zhwanwan
 * @create 2019-06-22 2:14 AM
 */
public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {

    @Override
    public void getRealNameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("接收到客户端信息: " + request.getUsername());
        responseObserver.onNext(MyResponse.newBuilder().setRealname("张三").build());
        responseObserver.onCompleted(); //服务端执行完毕
    }

    @Override
    public void getStudentByAge(StudentReq request, StreamObserver<StudentRep> responseObserver) {

        int age = request.getAge();

        System.out.println("收到客户端信息: " + request.getAge());
        responseObserver.onNext(StudentRep.newBuilder().setName("张三").setAge(age).setCity("北京").build());
        responseObserver.onNext(StudentRep.newBuilder().setName("李四").setAge(age).setCity("上海").build());
        responseObserver.onNext(StudentRep.newBuilder().setName("王五").setAge(age).setCity("广州").build());
        responseObserver.onNext(StudentRep.newBuilder().setName("赵六").setAge(age).setCity("苏州").build());

        responseObserver.onCompleted(); //服务端处理完毕
    }
}
