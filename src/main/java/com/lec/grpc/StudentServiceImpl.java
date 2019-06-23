package com.lec.grpc;

import com.lec.proto.*;
import io.grpc.stub.StreamObserver;

import java.util.UUID;

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

    /**
     * 服务端回调-->客户端请求对象
     *
     * @param responseObserver
     * @return
     */
    @Override
    public StreamObserver<StudentReq> getStudentsWrapperByAges(StreamObserver<StudentRepList> responseObserver) {

        return new StreamObserver<StudentReq>() {
            @Override
            public void onNext(StudentReq value) {
                System.out.println("onNext: " + value.getAge());
            }

            @Override
            public void onError(Throwable t) {
                //System.out.println(t.getMessage());
                System.out.println("Exception exists...");
            }

            @Override
            public void onCompleted() {
                StudentRep rep1 = StudentRep.newBuilder().setName("张三").setAge(20).setCity("北京").build();
                StudentRep rep2 = StudentRep.newBuilder().setName("李四").setAge(30).setCity("上海").build();

                StudentRepList repList = StudentRepList.newBuilder()
                        .addStudentRep(rep1).addStudentRep(rep2).build();
                responseObserver.onNext(repList);
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<StreamRequest> biTalk(StreamObserver<StreamResponse> responseObserver) {
        return new StreamObserver<StreamRequest>() {

            @Override
            public void onNext(StreamRequest value) {
                System.out.println(value.getRequestInfo());
                responseObserver.onNext(StreamResponse.newBuilder().setResponseInfo(UUID.randomUUID().toString()).build());
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
    }
}
